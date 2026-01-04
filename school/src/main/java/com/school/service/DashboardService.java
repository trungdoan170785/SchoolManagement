package com.school.service;

import java.time.LocalDate;
import java.util.Map;

public interface DashboardService {

    long totalStudents();

    long totalTeachers();

    long totalClasses();

    long totalCourses();

    double revenueThisMonth();

    Map<String, Long> attendanceToday();

    Map<String, Long> topCourses();
}
