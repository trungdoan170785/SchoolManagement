package com.school.service;

import com.school.entity.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    List<Notification> findAll();

    Optional<Notification> findById(Long id);

    Notification save(Notification notification);

    void deleteById(Long id);
}
