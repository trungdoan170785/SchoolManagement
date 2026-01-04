package com.school.service.impl;

import com.school.entity.Student;
import com.school.repository.Student1Repository;
import com.school.service.Student1Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Student1SeviceImpl implements Student1Service {
    private final Student1Repository repo;

    public Student1SeviceImpl(Student1Repository repo) {
        this.repo = repo;
    }

    @Override
    public List<Student> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Student save(Student s) {
        return repo.save(s);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public boolean existsByStudentCode(String code) {
        return repo.existsByStudentCode(code);
    }

    public List<Student> search(String keyword){
        return repo
                .findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }

}
