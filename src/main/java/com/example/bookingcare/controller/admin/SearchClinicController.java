package com.example.bookingcare.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;

@Controller("adminClinicController")
public class SearchClinicController {
	@Autowired
	private ClinicServiceImpl clinicService;

	@GetMapping("/admin/tim-kiem-csyt")
	public String listSpecialty(@RequestParam("name") String name, Model model) {

		// Gọi service để tìm kiếm specialties theo tên (không phân trang)
		List<Clinic> Clinics = clinicService.getClinicsByName(name);

		// Thêm danh sách specialties vào model
		model.addAttribute("Clinic", Clinics); // Thêm danh sách specialties
		model.addAttribute("searchName", name); // Tên đã tìm kiếm

		return "admin/searchClinic"; // Chuyển đến view danh sách specialties
	}

}