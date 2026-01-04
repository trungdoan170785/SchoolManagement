package com.school.service;

import com.school.entity.Teacher;
import java.util.List;

public interface Teacher1Service {
    List<Teacher> findAll();
    Teacher findById(Long id);
    Teacher save(Teacher teacher);
    void delete(Long id);
    List<Teacher> search(String keyword);
}