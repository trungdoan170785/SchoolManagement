package com.school.controller;

import com.school.dto.AttendanceRequest;
import com.school.entity.Attendance;
import com.school.entity.CourseClass;
import com.school.service.AttendanceService;
import com.school.service.CourseClassService;
import com.school.service.Student1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final CourseClassService courseClassService;
    private final Student1Service studentService;
    private final AttendanceService attendanceService;

    // 🔹 STEP 1 — HIỂN THỊ DANH SÁCH LỚP
    @GetMapping
    public String classList(Model model){
        model.addAttribute("classes", courseClassService.findAll());
        return "attendance/class-list";
    }

    // 🔹 STEP 2 — HIỂN THỊ DANH SÁCH HỌC VIÊN TRONG LỚP
    @GetMapping("/class/{classId}")
    public String takeAttendance(@PathVariable Long classId, Model model){

        var courseClass = courseClassService.findById(classId).orElseThrow();

        var students = courseClass.getEnrollments()
                .stream()
                .map(e -> e.getStudent())
                .toList();

        var today = LocalDate.now();

        // 🔥 lấy điểm danh của hôm nay
        var existing = attendanceService.findByClassAndDate(classId, today);

        // 🔥 map studentId -> status
        Map<Long, Attendance.Status> statusMap = existing.stream()
                .collect(Collectors.toMap(
                        a -> a.getStudent().getId(),
                        Attendance::getStatus,
                        (s1, s2) -> s1   // nếu trùng thì giữ giá trị cũ
                ));

        model.addAttribute("classInfo", courseClass);
        model.addAttribute("students", students);
        model.addAttribute("today", today);
        model.addAttribute("statusMap", statusMap);

        return "attendance/take-attendance";
    }

    // 🔹 STEP 3 — LƯU KẾT QUẢ
    @PostMapping("/class/{classId}")
    public String saveAttendance(
            @PathVariable Long classId,
            @ModelAttribute AttendanceRequest request
    ){

        var courseClass = courseClassService.findById(classId).orElseThrow();
        var today = LocalDate.now();

        // 🔥 lấy bản ghi đã tồn tại hôm nay
        var existing = attendanceService.findByClassAndDate(classId, today);

        Map<Long, Attendance> map = existing.stream()
                .collect(Collectors.toMap(
                        a -> a.getStudent().getId(),
                        a -> a
                ));

        for (AttendanceRequest.Item item : request.getRecords()) {

            var student = studentService.findById(item.getStudentId()).orElseThrow();

            Attendance att = map.getOrDefault(
                    student.getId(),
                    Attendance.builder()
                            .student(student)
                            .courseClass(courseClass)
                            .date(today)
                            .build()
            );

            att.setStatus(item.getStatus());

            attendanceService.save(att);
        }

        return "redirect:/attendance/class/" + classId;
    }

    @GetMapping("/history")
    public String showHistorySearch(
            @RequestParam(required = false) Long classId,
            Model model) {

        List<CourseClass> classes = courseClassService.findAll();
        model.addAttribute("classes", classes);
        model.addAttribute("selectedClassId", classId);

        // 🔥 Tìm class được chọn — tránh lỗi null
        CourseClass selectedClass = null;
        if (classId != null) {
            selectedClass = classes.stream()
                    .filter(c -> c.getId().equals(classId))
                    .findFirst()
                    .orElse(null);
        }

        model.addAttribute("selectedClass", selectedClass);

        return "attendance/history-search";
    }



    @PostMapping("/history")
    public String attendanceHistoryResult(
            @RequestParam Long classId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model
    ){

        var courseClass = courseClassService.findById(classId).orElseThrow();

        var records = attendanceService.findByClassAndDate(classId, date);

        model.addAttribute("classInfo", courseClass);
        model.addAttribute("date", date);
        model.addAttribute("records", records);

        return "attendance/history-result";
    }

}



