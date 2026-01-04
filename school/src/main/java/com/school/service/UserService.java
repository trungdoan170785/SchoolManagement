package com.school.service;

import com.school.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User createUserForEmail(String email, String password, List<Long> roleIds);
}
