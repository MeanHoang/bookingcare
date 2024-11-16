package com.example.bookingcare.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.doctor.DoctorService;
import com.example.bookingcare.service.doctor.SpecialtyService;

@Controller
public class SpecialtyController {
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private SpecialtyService specialtyService;

	@GetMapping("/danh-sach/chuyen-khoa/{id}")
	public String getSpecialtyDetail(@PathVariable("id") int id, Model model) {
		// Lấy thông tin chuyên khoa theo ID
		Specialty specialty = specialtyService.getSpecialtyById(id);
		model.addAttribute("specialty", specialty);
		List<Doctor> doctors = doctorService.findDoctorBySpecialtyId(id);
		model.addAttribute("doctors", doctors);

		return "user/specialtyDetail"; // Tên view trang chi tiết
	}

}
