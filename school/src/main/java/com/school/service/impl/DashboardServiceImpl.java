package com.school.service.impl;

import com.school.entity.Attendance;
import com.school.entity.Payment;
import com.school.repository.*;
import com.school.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final Student1Repository studentRepository;
    private final Teacher1Repository teacherRepository;
    private final CourseClassRepository courseClassRepository;
    private final CourseRepository courseRepository;
    private final PaymentRepository paymentRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    public long totalStudents() {
        System.out.println("Students = " + studentRepository.count());

        return studentRepository.count();
    }

    @Override
    public long totalTeachers() {
        return teacherRepository.count();
    }

    @Override
    public long totalClasses() {
        return courseClassRepository.count();
    }

    @Override
    public long totalCourses() {
        return courseRepository.count();
    }

    @Override
    public double revenueThisMonth() {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getPaidDate().getMonthValue() == LocalDate.now().getMonthValue())
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    @Override
    public Map<String, Long> attendanceToday() {
        List<Attendance> list =
                attendanceRepository.findByDate(LocalDate.now());

        return list.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getStatus().name(),
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Long> topCourses() {
        return courseClassRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        c -> c.getCourse().getName(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }
}
