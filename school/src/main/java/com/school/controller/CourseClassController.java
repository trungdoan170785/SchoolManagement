package com.school.controller;

import com.school.entity.CourseClass;
import com.school.service.CourseClassService;
import com.school.service.CourseService;
import com.school.service.Teacher1Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classes")
public class CourseClassController {

    private final CourseClassService classService;
    private final CourseService courseService;
    private final Teacher1Service teacher1Service;

    public CourseClassController(
            CourseClassService classService,
            CourseService courseService,
            Teacher1Service teacher1Service
    ) {
        this.classService = classService;
        this.courseService = courseService;
        this.teacher1Service = teacher1Service;
    }

    // LIST
    @GetMapping
    public String list(Model model) {
        model.addAttribute("classes", classService.findAll());
        return "classes/list";
    }

    // CREATE FORM
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("courseClass", new CourseClass());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", teacher1Service.findAll());
        model.addAttribute("title", "Tạo lớp học mới");
        return "classes/form";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        CourseClass cc = classService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid class id"));

        model.addAttribute("courseClass", cc);
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", teacher1Service.findAll());
        model.addAttribute("title", "Cập nhật lớp học");

        return "classes/form";
    }

    // SAVE
    @PostMapping("/save")
    public String save(@ModelAttribute("courseClass") CourseClass cc) {
        classService.save(cc);
        return "redirect:/classes";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        classService.deleteById(id);
        return "redirect:/classes";
    }
}
