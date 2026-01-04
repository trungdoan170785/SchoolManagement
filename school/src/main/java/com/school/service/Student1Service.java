package com.school.service;

import com.school.entity.Student;

import java.util.List;
import java.util.Optional;

public interface Student1Service {
    List<Student> findAll();

    Optional<Student> findById(Long id);

    Student save(Student s);

    void deleteById(Long id);

    boolean existsByStudentCode(String code);

    public List<Student> search(String keyword);

}
