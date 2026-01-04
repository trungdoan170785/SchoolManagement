package com.school.service;

import com.school.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    List<Payment> findAll();

    List<Payment> findByStudent(Long studentId);

    Optional<Payment> findById(Long id);

    Payment save(Payment payment);

    void deleteById(Long id);
}
