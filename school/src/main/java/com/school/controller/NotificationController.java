package com.school.controller;

import com.school.entity.Notification;
import com.school.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    // LIST
    @GetMapping
    public String list(Model model) {
        model.addAttribute("notifications", notificationService.findAll());
        return "notifications/list";
    }

    // CREATE FORM
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("notification", new Notification());
        return "notifications/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Notification notification) {
        notificationService.save(notification);
        return "redirect:/notifications";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("notification", notificationService.findById(id).orElseThrow());
        return "notifications/form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Notification notification) {
        notification.setId(id);
        notificationService.save(notification);
        return "redirect:/notifications";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        notificationService.deleteById(id);
        return "redirect:/notifications";
    }
}
