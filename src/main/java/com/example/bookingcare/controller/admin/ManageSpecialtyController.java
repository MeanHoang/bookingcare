package com.example.bookingcare.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.admin.AdminServiceImpl;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;
import com.example.bookingcare.service.user.RegistrationServiceImpl;

@Controller
public class ManageSpecialtyController {

	@Autowired
	private AdminServiceImpl adminService;

	@Autowired
	private ClinicServiceImpl clinicService;

	@Autowired
	private SpecialtyServiceImpl specialtyService;

	@Autowired
	private DoctorServiceImpl doctorService;

	@Autowired
	private RegistrationServiceImpl registrationService;

	@GetMapping("admin/quan-ly-chuyen-khoa")
	public String listSpecialty(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			Model model) {
		// Gọi service để lấy danh sách cơ sở y tế phân trang với page và size
		List<Specialty> specialtyList = specialtyService.getAllSpecialties(page, size);

		// Tính toán tổng số trang (dựa trên tổng số bản ghi và size)
		int totalPages = specialtyService.getTotalPages(size);

		// Thêm danh sách cơ sở y tế và thông tin phân trang vào model
		model.addAttribute("specialty", specialtyList); // Thêm danh sách clinic
		model.addAttribute("currentPage", page); // Trang hiện tại
		model.addAttribute("totalPages", totalPages); // Tổng số trang
		model.addAttribute("pageSize", size); // Kích thước mỗi trang

		return "admin/manageSpecialty"; // Chuyển đến view danh sách cơ sở y tế
	}

}
