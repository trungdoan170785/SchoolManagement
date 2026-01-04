package com.school.service;

import com.school.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Course save(Course c);

    void deleteById(Long id);

}
