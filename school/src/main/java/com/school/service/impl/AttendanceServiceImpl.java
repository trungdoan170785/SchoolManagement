package com.school.service.impl;

import com.school.entity.Attendance;
import com.school.repository.AttendanceRepository;
import com.school.repository.CourseClassRepository;
import com.school.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final CourseClassRepository courseClassRepository;

    @Override
    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    @Override
    public Optional<Attendance> findById(Long id) {
        return attendanceRepository.findById(id);
    }

    @Override
    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteById(Long id) {
        attendanceRepository.deleteById(id);
    }


    @Override
    public List<Attendance> findTodayAttendance(Long classId) {
        return attendanceRepository.findByCourseClassIdAndDate(classId, LocalDate.now());
    }

    @Override
    public List<Attendance> findByCourseClassIdAndDate(Long classId, LocalDate date) {
        return List.of();
    }

    @Override
    public List<Attendance> findByClassAndDate(Long classId, LocalDate date) {
        return attendanceRepository.findByCourseClassIdAndDate(classId, date);
    }
}
