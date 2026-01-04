package com.school.service.impl;

import com.school.entity.Staff;
import com.school.repository.StaffRepository;
import com.school.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository repo;

    public List<Staff> findAll(){ return repo.findAll(); }
    public Staff findById(Long id){ return repo.findById(id).orElse(null); }
    public Staff save(Staff s){ return repo.save(s); }
    public void delete(Long id){ repo.deleteById(id); }

    public List<Staff> search(String keyword){
        return repo
                .findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }

}
