package com.school.service;

import com.school.entity.Staff;
import java.util.List;

public interface StaffService {
    List<Staff> findAll();
    Staff findById(Long id);
    Staff save(Staff staff);
    void delete(Long id);
    public List<Staff> search(String keyword);

}
