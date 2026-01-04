package com.school.controller;

import com.school.entity.Staff;
import com.school.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staffs")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // LIST
    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("staffs", staffService.findAll());
        return "staffs/list";
    }

    // FORM CREATE
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("staff", new Staff());
        model.addAttribute("title", "Thêm nhân sự");
        return "staffs/form";
    }

    // SAVE (CREATE + UPDATE)
    @PostMapping("/save")
    public String save(@ModelAttribute("staff") Staff staff) {
        staffService.save(staff);
        return "redirect:/staffs";
    }

    // EDIT
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Staff staff = staffService.findById(id);
        model.addAttribute("staff", staff);
        model.addAttribute("title", "Cập nhật nhân sự");
        return "staffs/form";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        staffService.delete(id);
        return "redirect:/staffs";
    }
}
