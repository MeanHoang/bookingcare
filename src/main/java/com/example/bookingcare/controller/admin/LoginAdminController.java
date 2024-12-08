package com.example.bookingcare.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.bookingcare.model.Admins;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.admin.AdminServiceImpl;
import com.example.bookingcare.service.doctor.DoctorService;

@Controller
@SessionAttributes("admin") // Lưu đối tượng admin vào session
public class LoginAdminController {

	@Autowired
	private AdminServiceImpl adminService;

	// Trang đăng nhập
	@GetMapping("/admin/login")
	public String showLoginPage() {
		return "admin/loginAdmin"; // Trang login của admin
	}

	// Xử lý đăng nhập
	@PostMapping("/admin/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		// Tìm kiếm bác sĩ từ service
		Admins admin = adminService.login(username, password);

		// Kiểm tra xem bác sĩ có được tìm thấy không
		if (admin != null) {
			model.addAttribute("admin", admin); // Lưu thông tin bác sĩ vào session

			// Debug: Kiểm tra giá trị của bác sĩ đã lưu trong session
			System.out.println("Thông tin admin trong session: " + model.getAttribute("admin"));

			return "redirect:/admin/home"; // Chuyển hướng đến trang chủ của bác sĩ
		} else {
			model.addAttribute("error", "Thông tin đăng nhập không đúng");

			// Debug: In ra lỗi nếu không tìm thấy bác sĩ
			System.out.println("Thông tin đăng nhập không đúng.");

			return "admin/loginAdmin"; // Nếu đăng nhập không thành công, quay lại trang login
		}
	}

	// Đăng xuất
	@GetMapping("/admin/logout")
	public String logout(SessionStatus status) {
		// Xóa thông tin trong session khi bác sĩ đăng xuất
		status.setComplete();

		// Debug: Kiểm tra sau khi logout
		System.out.println("Đăng xuất thành công.");

		return "redirect:/doctor/loginDoctor"; // Quay lại trang đăng nhập
	}
}
