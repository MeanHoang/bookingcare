package com.example.bookingcare.controller.admin;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.doctor.DoctorService;

@Controller
public class ManageDoctorController {

	@Autowired
	private DoctorService doctorService;

	// Phương thức hiển thị danh sách bác sĩ
	@GetMapping("/admin/quan-ly-bac-sy")
	public String listDoctor(Model model) {
		// Lấy danh sách bác sĩ từ service
		List<Doctor> doctorList = doctorService.getAllDoctors();
		// Thêm danh sách bác sĩ vào model
		model.addAttribute("doctors", doctorList);
		return "admin/manageDoctor";
	}

	@GetMapping("/admin/thong-tin-bac-sy/{id}")
	public String viewDoctor(@PathVariable("id") int id, Model model) throws SQLException {
		// Lấy thông tin bác sĩ theo id từ service
		Doctor doctor = doctorService.getDoctorById(id);
		// Thêm thông tin bác sĩ vào model
		model.addAttribute("doctor", doctor);
		return "admin/doctorDetail";
	}

}