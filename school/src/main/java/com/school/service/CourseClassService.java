package com.school.service;

import com.school.entity.CourseClass;
import java.util.List;
import java.util.Optional;

public interface CourseClassService {

    public List<CourseClass> findAll();

    public Optional<CourseClass> findById(Long id) ;

    public CourseClass save(CourseClass c);

    public void deleteById(Long id);
}
