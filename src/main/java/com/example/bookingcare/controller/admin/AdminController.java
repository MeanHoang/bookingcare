package com.example.bookingcare.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Admins;
import com.example.bookingcare.service.admin.AdminServiceImpl;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;
import com.example.bookingcare.service.user.RegistrationServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

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

	@GetMapping("/admin/home")
	public String home(HttpSession session, Model model) {
		// Kiểm tra xem admin đã đăng nhập chưa
		Admins admin = (Admins) session.getAttribute("admin");

		int soLuongThang10 = registrationService.CountRegistrationByMonth(10);
		int soLuongThang11 = registrationService.CountRegistrationByMonth(11);
		int soLuongThang12 = registrationService.CountRegistrationByMonth(12);
		System.out.println("soLuongThang12: " + soLuongThang12);
		if (admin == null) {
			return "redirect:/admin/login";
		}

		model.addAttribute("soLuongThang10", soLuongThang10); // Thêm danh sách bác sĩ
		model.addAttribute("soLuongThang11", soLuongThang11); // Thêm danh sách bác sĩ
		model.addAttribute("soLuongThang12", soLuongThang12); // Thêm danh sách bác sĩ

		// Nếu đã đăng nhập, cho phép vào trang home
		return "admin/dashBoard";
	}

	@GetMapping("/addAdmin")
	public String addAdmin() {

		return "admin/addAdmin";
	}

	@PostMapping("/submit_registration")
	public String submitRegistration(@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("password") String password) throws SQLException {
		Admins ad1 = new Admins();
		ad1.setName(name);
		ad1.setUsername(username);
		ad1.setPassword(password);
		adminService.addAdmin(ad1);

		return "home";
	}

}
