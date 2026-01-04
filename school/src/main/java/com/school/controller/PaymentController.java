package com.school.controller;

import com.school.entity.Payment;
import com.school.service.PaymentService;
import com.school.service.Student1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final Student1Service studentService;

    // DANH SÁCH
    @GetMapping
    public String listPayments(Model model) {
        model.addAttribute("payments", paymentService.findAll());
        return "payments/list";
    }

    // FORM CREATE
    @GetMapping("/create")
    public String createForm(Model model) {
        Payment p = new Payment();
        p.setPaidDate(LocalDate.now());

        model.addAttribute("payment", p);
        model.addAttribute("students", studentService.findAll());

        return "payments/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Payment payment) {
        paymentService.save(payment);
        return "redirect:/payments";
    }

    // FORM EDIT
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Payment payment = paymentService.findById(id).orElseThrow();
        model.addAttribute("payment", payment);
        model.addAttribute("students", studentService.findAll());

        return "payments/form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Payment payment) {
        payment.setId(id);
        paymentService.save(payment);
        return "redirect:/payments";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        paymentService.deleteById(id);
        return "redirect:/payments";
    }
}
