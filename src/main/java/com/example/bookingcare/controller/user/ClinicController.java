package com.example.bookingcare.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.doctor.ClinicService;
import com.example.bookingcare.service.doctor.DoctorService;

@Controller
public class ClinicController {
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ClinicService clinicService;

	@GetMapping("/danh-sach/co-so-y-te/{id}")
	public String getClinicDetail(@PathVariable("id") int id, Model model) {
		// Lấy thông tin chuyên khoa theo ID
		Clinic clinic = clinicService.getClinicById(id);
		model.addAttribute("clinic", clinic);
		List<Doctor> doctors = doctorService.findDoctorByClinicId(id);
		model.addAttribute("doctors", doctors);

		return "user/clinicDetail"; // Tên view trang chi tiết
	}

}
