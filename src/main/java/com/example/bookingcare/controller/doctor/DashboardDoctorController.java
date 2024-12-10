package com.example.bookingcare.controller.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.doctor.DoctorService;
import com.example.bookingcare.service.user.RegistrationService;

@Controller
@SessionAttributes("doctor")
public class DashboardDoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private RegistrationService registrationService;

	@GetMapping("/doctor/trang-chu")
	public String home(Model model) {

		if (model.getAttribute("doctor") == null) {
			return "redirect:/doctor/login"; // Chuyển hướng đến trang đăng nhập
		}

		Doctor doctor = (Doctor) model.getAttribute("doctor");

		int doctorId = doctor.getId();
		model.addAttribute("message", "Chào mừng đến với trang chủ!");

		model.addAttribute("doctor", model.getAttribute("doctor"));

		int soLuongThang10 = registrationService.CountRegistrationByMonth(10);
		int soLuongThang11 = registrationService.CountRegistrationByMonth(11);
		int soLuongThang12 = registrationService.CountRegistrationByMonth(12);
		System.out.println("soLuongThang12: " + soLuongThang12);

		model.addAttribute("soLuongThang10", soLuongThang10);
		model.addAttribute("soLuongThang11", soLuongThang11);
		model.addAttribute("soLuongThang12", soLuongThang12);

		int soLuongNguoiDung = registrationService.countUserByDoctorID(doctorId);
		int soLuongDaKham = registrationService.countRegistrationByDoctorIdAndIsActive(doctorId);
		int doanhThu = registrationService.countRevenueByDoctorIdAndIsActive(doctorId);
		int soLuongDonCanDuyet = registrationService.countRegistrationByDoctorIdAndIsNotActive(doctorId);

		model.addAttribute("soLuongNguoiDung", soLuongNguoiDung);
		model.addAttribute("soLuongDaKham", soLuongDaKham);
		model.addAttribute("doanhThu", doanhThu);
		model.addAttribute("soLuongDonCanDuyet", soLuongDonCanDuyet);

		int soLuongNguoiDungTuHaNoi = registrationService.countUserFromByDoctorId("Hà Nội", doctorId);
		System.out.println("check soLuongNguoiDungTuHaNoi:" + soLuongNguoiDungTuHaNoi);
		int soLuongNguoiDungTuHCM = registrationService.countUserFromByDoctorId("Hồ Chí Minh", doctorId);

		model.addAttribute("soLuongNguoiDungTuHaNoi", soLuongNguoiDungTuHaNoi);
		model.addAttribute("soLuongNguoiDungTuHCM", soLuongNguoiDungTuHCM);
		return "doctor/dashboard";
	}
}
