package com.school.service.impl;

import com.school.entity.Course;
import com.school.repository.CourseRepository;
import com.school.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;

    public List<Course> findAll() {
        return repo.findAll();
    }

    public Optional<Course> findById(Long id) {
        return repo.findById(id);
    }

    public Course save(Course c) {
        return repo.save(c);
    }


    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public boolean codeExists(String code) {
        return repo.existsByCode(code);
    }
}
