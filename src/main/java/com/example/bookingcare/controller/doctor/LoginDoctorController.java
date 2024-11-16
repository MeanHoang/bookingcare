package com.example.bookingcare.controller.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.doctor.DoctorService;

@Controller
@SessionAttributes("doctor") // Lưu đối tượng bác sĩ vào session
public class LoginDoctorController {

	@Autowired
	private DoctorService doctorService;

	// Trang đăng nhập
	@GetMapping("/doctor/login")
	public String showLoginPage() {
		return "doctor/loginDoctor"; // Trang login của bác sĩ
	}

	// Xử lý đăng nhập
	@PostMapping("/doctor/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		// Tìm kiếm bác sĩ từ service
		Doctor doctor = doctorService.login(username, password);

		// Kiểm tra xem bác sĩ có được tìm thấy không
		if (doctor != null) {
			model.addAttribute("doctor", doctor); // Lưu thông tin bác sĩ vào session

			// Debug: Kiểm tra giá trị của bác sĩ đã lưu trong session
			System.out.println("Bác sĩ đăng nhập: " + doctor);
			System.out.println("Thông tin bác sĩ trong session: " + model.getAttribute("doctor"));

			return "redirect:/doctor/trang-chu"; // Chuyển hướng đến trang chủ của bác sĩ
		} else {
			model.addAttribute("error", "Thông tin đăng nhập không đúng");

			// Debug: In ra lỗi nếu không tìm thấy bác sĩ
			System.out.println("Thông tin đăng nhập không đúng.");

			return "doctor/loginDoctor"; // Nếu đăng nhập không thành công, quay lại trang login
		}
	}

	// Đăng xuất
	@GetMapping("/doctor/logout")
	public String logout(SessionStatus status) {
		// Xóa thông tin trong session khi bác sĩ đăng xuất
		status.setComplete();

		// Debug: Kiểm tra sau khi logout
		System.out.println("Đăng xuất thành công.");

		return "redirect:/doctor/loginDoctor"; // Quay lại trang đăng nhập
	}
}
