package com.school.service.impl;

import com.school.entity.Teacher;
import com.school.repository.Teacher1Repository;
import com.school.service.Teacher1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Teacher1ServiceImpl implements Teacher1Service {

    @Autowired
    private Teacher1Repository repo;

    public List<Teacher> findAll(){ return repo.findAll(); }

    public Teacher findById(Long id){ return repo.findById(id).orElse(null); }

    public Teacher save(Teacher t){ return repo.save(t); }

    public void delete(Long id){ repo.deleteById(id); }
    public List<Teacher> search(String keyword){
        return repo
                .findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }
}
