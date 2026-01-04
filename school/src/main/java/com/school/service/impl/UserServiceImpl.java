package com.school.service.impl;

import com.school.entity.User;
import com.school.repository.RoleRepository;
import com.school.repository.UserRepository;
import com.school.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUserForEmail(String email,
                                   String rawPassword,
                                   List<Long> roleIds){

        if(userRepository.existsByUsername(email)){
            throw new RuntimeException("Email này đã có user rồi!");
        }

        User user = new User();
        user.setUsername(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEnabled(true);

        user.setRoles(
                Set.copyOf(roleRepository.findAllById(roleIds))
        );

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


}