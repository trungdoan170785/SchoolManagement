package com.school.controller;

import com.school.entity.Staff;
import com.school.entity.Student;
import com.school.entity.Teacher;
import com.school.entity.User;
import com.school.service.StaffService;
import com.school.service.Student1Service;
import com.school.service.Teacher1Service;
import com.school.service.UserService;
import com.school.service.impl.UserServiceImpl;
import com.school.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final Student1Service studentService;
    private final Teacher1Service teacherService;
    private final StaffService staffService;
    private final RoleRepository roleRepository;
    private final UserService userService;


    @GetMapping
    public String list(Model model){
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/select")
    public String selectUserSource(Model model){

        model.addAttribute("students", studentService.findAll());
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("staffs", staffService.findAll());

        return "users/select";
    }

    @GetMapping("/create")
    public String searchForm(){
        return "users/search";
    }

    @GetMapping("/search-result")
    public String searchResult(@RequestParam String keyword, Model model){

        List<Teacher> teachers = teacherService.search(keyword);
        List<Student> students = studentService.search(keyword);
        List<Staff> staffs = staffService.search(keyword);

        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        model.addAttribute("staffs", staffs);

        return "users/search-result";
    }

    @GetMapping("/create-from-email")
    public String createFromEmail(@RequestParam String email, Model model){

        User user = new User();
        user.setUsername(email);

        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());

        return "users/create-from-email";
    }

    @PostMapping("/create-from-email")
    public String saveCreateUser(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam List<Long> roleIds){

        userService.createUserForEmail(username, password, roleIds);

        return "redirect:/users";
    }
}
