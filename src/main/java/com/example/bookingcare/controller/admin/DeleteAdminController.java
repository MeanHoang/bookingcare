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
public class DeleteAdminController {

	@Autowired
	private AdminServiceImpl adminService;

	@GetMapping("/delete-admin/{id}")
	public String adminDetail(@PathVariable("id") int id, Model model) {
		Admins admin = adminService.getAdminByID(id);
		model.addAttribute("admin", admin);
		System.out.println(admin);
		return "admin/deleteConfirm";
	}
//"/submit_update"

	@PostMapping("/submit_delete")
	public String submitRegistration(@RequestParam("ID") int ID) throws SQLException {
		System.out.println("Deleting admin with ID: " + ID);
		int adminId = ID;
		adminService.deleteAdmin(adminId);
		return "home";
	}

}
