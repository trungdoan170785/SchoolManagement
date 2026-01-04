package com.school.repository;

import com.school.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name, String email);
}
