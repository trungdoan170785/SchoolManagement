package com.school.service.impl;

import com.school.entity.Enrollment;
import com.school.repository.Enrollment1Repository;
import com.school.service.Enrollment1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Enrollment1ServiceImpl implements Enrollment1Service {

    private final Enrollment1Repository enrollmentRepository;

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Optional<Enrollment> findById(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
