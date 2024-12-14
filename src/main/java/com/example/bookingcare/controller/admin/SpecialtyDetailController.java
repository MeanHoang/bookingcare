package com.example.bookingcare.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller
public class SpecialtyDetailController {

    @Autowired
    private SpecialtyServiceImpl specialtyService;

    @GetMapping("/admin/thong-tin-chuyen-khoa")
    public String viewSpecialtyDetail(@RequestParam("id") int id, Model model) {
        Specialty specialty = specialtyService.getSpecialtyById(id);

        if (specialty != null) {
            model.addAttribute("specialty", specialty);
            return "admin/specialtyDetail";
        } else {
            return "redirect:/admin/quan-ly-chuyen-khoa";
        }
    }
}
