package com.school.controller;

import com.school.entity.Student;
import com.school.service.Student1Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final Student1Service service;

    public StudentController(Student1Service service) {
        this.service = service;
    }

    // LIST
    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", service.findAll());
        return "students/list";
    }

    // CREATE FORM
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("title", "Thêm học viên");
        return "students/form";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Student s = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Student ID"));

        model.addAttribute("student", s);
        model.addAttribute("title", "Cập nhật học viên");
        return "students/form";
    }

    // SAVE
    @PostMapping("/create")
    public String save(@ModelAttribute("student") Student s) {
        service.save(s);
        return "redirect:/students";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/students";
    }
}
