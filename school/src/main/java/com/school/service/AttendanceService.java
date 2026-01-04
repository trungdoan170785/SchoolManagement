package com.school.service;

import com.school.entity.Attendance;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    List<Attendance> findAll();

    Optional<Attendance> findById(Long id);

    Attendance save(Attendance attendance);

    void deleteById(Long id);

    List<Attendance> findByClassAndDate(Long classId, LocalDate date);

    List<Attendance> findTodayAttendance(Long classId);

    List<Attendance> findByCourseClassIdAndDate(Long classId, LocalDate date);

}
