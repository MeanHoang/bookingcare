package com.example.bookingcare.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;
import com.example.bookingcare.service.user.RegistrationServiceImpl;

@Controller
public class ManageDoctorController {

	@Autowired
	private ClinicServiceImpl clinicService;

	@Autowired
	private SpecialtyServiceImpl specialtyService;

	@Autowired
	private DoctorServiceImpl doctorService;

	@Autowired
	private RegistrationServiceImpl registrationService;

	@GetMapping("admin/quan-ly-bac-sy")
	public String listDoctors(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			Model model) {

		// Gọi service để lấy danh sách bác sĩ phân trang với page và size
		List<Doctor> doctorList = doctorService.getAllDoctors(page, size);

		// Tính toán tổng số trang (dựa trên tổng số bản ghi và size)
		int totalPages = doctorService.getTotalPages(size);

		// Thêm danh sách bác sĩ và thông tin phân trang vào model
		model.addAttribute("doctors", doctorList); // Thêm danh sách bác sĩ
		model.addAttribute("currentPage", page); // Trang hiện tại
		model.addAttribute("totalPages", totalPages); // Tổng số trang
		model.addAttribute("pageSize", size); // Kích thước mỗi trang

		return "admin/manageDoctor"; // Chuyển đến view danh sách bác sĩ
	}

}
