package com.example.bookingcare.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Admins;
import com.example.bookingcare.service.admin.AdminServiceImpl;

@Controller
public class UpdateAdminController {

	@Autowired
	private AdminServiceImpl adminService;

	@GetMapping("/admin-detail/{id}")
	public String adminDetail(@PathVariable("id") int id, Model model) {
		Admins admin = adminService.getAdminByID(id);
		model.addAttribute("admin", admin);
		System.out.println(admin);
		return "admin/adminDetail";
	}
//"/submit_update"

	@PostMapping("/submit_update")
	public String submitRegistration(@RequestParam("ID") int ID, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("name") String name) throws SQLException {
		Admins ad1 = new Admins();
		ad1.setID(ID);
		ad1.setName(name);
		ad1.setPassword(password);
		ad1.setUsername(username);
		adminService.updateAdmin(ad1);

		return "home";
	}

}
