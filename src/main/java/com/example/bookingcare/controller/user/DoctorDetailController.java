package com.example.bookingcare.controller.user;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.doctor.DoctorService;

@Controller
public class DoctorDetailController {
	@Autowired
	private DoctorService doctorService;

	@GetMapping("/danh-sach/bac-sy/{id}")
	public String getSpecialtyDetail(@PathVariable("id") int id, Model model) throws SQLException {

		Doctor doctor = doctorService.getDoctorById(id);
		System.out.print(doctor);
		model.addAttribute("doctor", doctor);

		return "user/doctorDetail";
	}

}