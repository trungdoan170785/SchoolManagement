package com.school.repository;

import com.school.entity.Attendance;
import com.school.entity.CourseClass;
import com.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByCourseClass(CourseClass courseClass);

    List<Attendance> findByStudent(Student student);

    List<Attendance> findByCourseClassAndDate(CourseClass courseClass, LocalDate date);

    boolean existsByStudentAndCourseClassAndDate(Student student, CourseClass courseClass, LocalDate date);

    List<Attendance> findByCourseClassIdAndDate(Long classId, LocalDate date);

    List<Attendance> findByDate(LocalDate date);

}
