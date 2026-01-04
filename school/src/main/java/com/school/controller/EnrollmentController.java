package com.school.controller;

import com.school.entity.Enrollment;
import com.school.service.Enrollment1Service;
import com.school.service.CourseClassService;
import com.school.service.Student1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final Enrollment1Service enrollmentService;
    private final Student1Service studentService;
    private final CourseClassService courseClassService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("enrollments", enrollmentService.findAll());
        return "enrollments/list";
    }

    @GetMapping("/create")
    public String createForm(Model model){

        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("classes", courseClassService.findAll());

        return "enrollments/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Enrollment enrollment){

        var student = studentService.findById(enrollment.getStudent().getId())
                .orElseThrow();

        var courseClass = courseClassService.findById(enrollment.getCourseClass().getId())
                .orElseThrow();

        enrollment.setStudent(student);
        enrollment.setCourseClass(courseClass);

        if(enrollment.getEnrolledDate() == null){
            enrollment.setEnrolledDate(LocalDate.now());
        }

        enrollmentService.save(enrollment);

        return "redirect:/enrollments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        enrollmentService.delete(id);
        return "redirect:/enrollments";
    }
}
