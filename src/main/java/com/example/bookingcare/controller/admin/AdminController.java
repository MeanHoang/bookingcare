package com.example.bookingcare.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bookingcare.model.Admins;
import com.example.bookingcare.service.admin.AdminServiceImpl;

@Controller
public class AdminController {

	@Autowired
	private AdminServiceImpl adminService;

	@GetMapping("/home")
	public String home(Model model) {
		List<Admins> adminsList = adminService.getAllAdmins();
		model.addAttribute("admins", adminsList);
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
