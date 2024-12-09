package com.example.bookingcare.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller
public class DeleteSpecialtyController {

	@Autowired
    private SpecialtyServiceImpl specialtyService;

	@PostMapping("admin/xoa-chuyen-khoa")
	public String submitRegistration(@RequestParam("id") int ID) throws SQLException {
		System.out.println("Deleting admin with ID: " + ID);
		int specialtyId = ID;
		specialtyService.deleteSpecialty(specialtyId);
		return "redirect:/admin/quan-ly-chuyen-khoa";
	}

}
