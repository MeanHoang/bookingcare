package com.example.bookingcare.controller.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.doctor.DoctorService;

@Controller
@SessionAttributes("doctor")
public class DoctorProfileController {

	@Autowired
	private DoctorService doctorService; // Dịch vụ lấy thông tin bác sĩ

	// Xem thông tin cá nhân của bác sĩ
	@GetMapping("/doctor/thong-tin-ca-nhan")
	public String viewUserProfile(@ModelAttribute("doctor") Doctor doctor, Model model) {
		if (doctor == null) {
			// Nếu không có thông tin bác sĩ trong session, chuyển hướng đến login
			System.out.println("Bác sĩ chưa đăng nhập, chuyển hướng đến trang đăng nhập.");
			return "redirect:/doctor/login"; // Chuyển hướng đến trang đăng nhập
		}

		// Debug: In thông tin bác sĩ ra để kiểm tra
		System.out.println("Thông tin bác sĩ: " + doctor);

		// Đảm bảo rằng bác sĩ được thêm vào model để hiển thị
		model.addAttribute("doctor", doctor);

		// Chuyển đến trang thông tin bác sĩ
		return "doctor/doctorProfile";
	}
}
