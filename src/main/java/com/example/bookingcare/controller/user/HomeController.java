package com.example.bookingcare.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.doctor.ClinicService;
import com.example.bookingcare.service.doctor.DoctorService;
import com.example.bookingcare.service.doctor.SpecialtyService;

@Controller
public class HomeController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ClinicService clinicService;

	@Autowired
	private SpecialtyService specialtyService;

	@GetMapping("/")
	public String home(Model model) {
		// Truyền thông điệp vào view
		model.addAttribute("message", "Chào mừng đến với trang chủ!");
		return "user/home";
	}

	// Phương thức hiển thị danh sách bác sĩ
	@GetMapping("/danh-sach/bac-sy")
	public String listDoctor(Model model) {
		// Lấy danh sách bác sĩ từ service
		List<Doctor> doctorList = doctorService.getAllDoctors();
		// Thêm danh sách bác sĩ vào model
		model.addAttribute("doctors", doctorList);
		return "user/listDoctor"; // Chuyển đến view danh sách bác sĩ
	}

	// Phương thức hiển thị danh sách cơ sở y tế
	@GetMapping("/danh-sach/co-so-y-te")
	public String listClinic(Model model) {
		// Lấy danh sách cơ sở y tế từ service
		List<Clinic> clinicList = clinicService.getAllClinics();
		// Thêm danh sách cơ sở y tế vào model
		model.addAttribute("clinics", clinicList);
		return "user/listClinic"; // Chuyển đến view danh sách cơ sở y tế
	}

	// Phương thức hiển thị danh sách chuyên khoa
	@GetMapping("/danh-sach/chuyen-khoa")
	public String listSpecialty(Model model) {
		// Lấy danh sách chuyên khoa từ service
		List<Specialty> specialtyList = specialtyService.getAllSpecialties();
		// Thêm danh sách chuyên khoa vào model
		model.addAttribute("specialties", specialtyList);
		return "user/listSpecialty"; // Chuyển đến view danh sách chuyên khoa
	}
}