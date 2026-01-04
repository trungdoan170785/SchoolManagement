package com.school.repository;

import com.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Student1Repository extends JpaRepository<Student, Long> {
    boolean existsByStudentCode(String studentCode);
    List<Student> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name, String email);

}