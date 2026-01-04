package com.school.service;

import com.school.entity.Enrollment;

import java.util.List;
import java.util.Optional;

public interface Enrollment1Service {

    List<Enrollment> findAll();

    Optional<Enrollment> findById(Long id);

    Enrollment save(Enrollment enrollment);

    void delete(Long id);
}
