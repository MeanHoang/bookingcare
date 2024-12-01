package com.example.bookingcare.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller
public class DeleteClinicController {

	@Autowired
	private ClinicServiceImpl clinicService;

	@PostMapping("admin/xoa-csyt")
	public String submitRegistration(@RequestParam("id") int ID) throws SQLException {
		System.out.println("Deleting clinic with ID: " + ID);
		int specialtyId = ID;
		clinicService.deleteClinic(specialtyId);
		return "redirect:/admin/quan-ly-csyt";
	}

}
