package com.school.service.impl;

import com.school.entity.CourseClass;
import com.school.repository.CourseClassRepository;
import com.school.service.CourseClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseClassServiceImpl implements CourseClassService {

    private final CourseClassRepository repo;


    public List<CourseClass> findAll() {
        return repo.findAll();
    }

    public Optional<CourseClass> findById(Long id) {
        return repo.findById(id);
    }

    public CourseClass save(CourseClass c) {
        return repo.save(c);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
