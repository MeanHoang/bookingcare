package com.example.bookingcare.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Admins;
import com.example.bookingcare.service.admin.AdminServiceImpl;

@Controller
public class AddAdminController {

	@Autowired
	private AdminServiceImpl adminService;

	@GetMapping("/addAdmin")
	public String addAdmin() {

		return "admin/addAdmin";
	}

	@PostMapping("/submit_registration")
	public String submitRegistration(@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("password") String password) throws SQLException {
		Admins ad1 = new Admins();
		ad1.setName(name);
		ad1.setUsername(username);
		ad1.setPassword(password);
		adminService.addAdmin(ad1);

		return "home";
	}
}
