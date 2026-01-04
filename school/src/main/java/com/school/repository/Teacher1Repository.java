package com.school.repository;

import com.school.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Teacher1Repository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name, String email);
}