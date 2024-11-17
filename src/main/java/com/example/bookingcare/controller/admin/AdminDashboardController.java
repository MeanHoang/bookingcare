package com.example.bookingcare.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bookingcare.service.doctor.DoctorService;

@Controller
public class AdminDashboardController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping("/admin/trang-chu")
	public String home(Model model) {
		model.addAttribute("message", "Chào mừng đến với trang chủ!");

		return "admin/dashboard";
	}
}
