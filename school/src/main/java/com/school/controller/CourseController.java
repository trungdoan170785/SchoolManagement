package com.school.controller;

import com.school.entity.Course;
import com.school.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    // LIST
    @GetMapping
    public String list(Model model) {
        model.addAttribute("courses", service.findAll());
        return "courses/list";
    }

    // CREATE FORM
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("title", "Thêm khóa học");
        return "courses/form";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Course c = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Course ID"));

        model.addAttribute("course", c);
        model.addAttribute("title", "Cập nhật khóa học");
        return "courses/form";
    }

    // SAVE
    @PostMapping("/save")
    public String save(@ModelAttribute("course") Course c) {
        service.save(c);
        return "redirect:/courses";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/courses";
    }
}
