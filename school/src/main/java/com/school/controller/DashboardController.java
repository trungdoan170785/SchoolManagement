package com.school.controller;

import com.school.service.DashboardService;
import com.school.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    private final NotificationService notificationService;

    @GetMapping("/dashboards, /")
    public String dashboard(Model model) {

        model.addAttribute("students", dashboardService.totalStudents());
        model.addAttribute("teachers", dashboardService.totalTeachers());
        model.addAttribute("classes", dashboardService.totalClasses());
        model.addAttribute("courses", dashboardService.totalCourses());

        model.addAttribute("revenue", dashboardService.revenueThisMonth());

        // LẤY MAP TỪ SERVICE
        Map<String, Long> attendance = dashboardService.attendanceToday();

        // GÁN RIÊNG TỪNG BIẾN
        model.addAttribute("present", attendance.getOrDefault("PRESENT", 0L));
        model.addAttribute("absent", attendance.getOrDefault("ABSENT", 0L));
        model.addAttribute("late", attendance.getOrDefault("LATE", 0L));
        model.addAttribute("excused", attendance.getOrDefault("EXCUSED", 0L));

        model.addAttribute("topCourses", dashboardService.topCourses());

        model.addAttribute("notifications",
                notificationService.findAll().stream().limit(5).toList());

        return "reports/dashboard";
    }
}
