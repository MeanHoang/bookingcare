package com.example.bookingcare.controller.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bookingcare.service.doctor.DoctorService;
import com.example.bookingcare.service.user.RegistrationService;

@Controller
@SessionAttributes("doctor")
public class DashboardDoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private RegistrationService registrationService;

	// Trang chủ của bác sĩ
	@GetMapping("/doctor/trang-chu")
	public String home(Model model) {
		// Kiểm tra xem bác sĩ đã đăng nhập chưa (kiểm tra xem session có chứa đối tượng
		// "doctor")
		if (model.getAttribute("doctor") == null) {
			// Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
			return "redirect:/doctor/login"; // Chuyển hướng đến trang đăng nhập
		}

		// Truyền thông điệp vào view khi bác sĩ đã đăng nhập thành công
		model.addAttribute("message", "Chào mừng đến với trang chủ!");

		// Truyền thêm đối tượng bác sĩ vào view (nếu cần thiết)
		model.addAttribute("doctor", model.getAttribute("doctor"));

		int soLuongThang10 = registrationService.CountRegistrationByMonth(10);
		int soLuongThang11 = registrationService.CountRegistrationByMonth(11);
		int soLuongThang12 = registrationService.CountRegistrationByMonth(12);
		System.out.println("soLuongThang12: " + soLuongThang12);

		model.addAttribute("soLuongThang10", soLuongThang10); // Thêm danh sách bác sĩ
		model.addAttribute("soLuongThang11", soLuongThang11); // Thêm danh sách bác sĩ
		model.addAttribute("soLuongThang12", soLuongThang12); // Thêm danh sách bác sĩ
		// Hiển thị trang chủ của bác sĩ
		return "doctor/dashboard";
	}
}
