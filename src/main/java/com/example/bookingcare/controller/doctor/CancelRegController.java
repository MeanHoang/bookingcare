package com.example.bookingcare.controller.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Registration;
import com.example.bookingcare.service.doctor.DoctorService;
import com.example.bookingcare.service.doctor.ScheduleService;
import com.example.bookingcare.service.user.EmailService;
import com.example.bookingcare.service.user.RegistrationService;

@Controller
@SessionAttributes("doctor")
public class CancelRegController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private EmailService emailService;

	@PostMapping("/doctor/cancel-reg")
	public String cancelRegistration(@RequestParam("registrationId") int registrationId, Model model) {

		System.out.println("check regid: " + registrationId);

		Registration registration = registrationService.getRegistrationById(registrationId);
		System.out.println("check reg: " + registration);
		int scheduleID = registration.getScheduleId();

		try {
			registrationService.deleteRegistration(registrationId);
			scheduleService.isNotUsedSchedule(scheduleID);
			System.out.println("Đã xóa thành công!");
			model.addAttribute("message", "Đã hủy đặt lịch thành công");
		} catch (Exception e) {
			model.addAttribute("message", "Có lỗi xảy ra khi hủy đặt lịch");
		}

		Object doctor = model.getAttribute("doctor");
		if (doctor == null) {
			return "redirect:/doctor/login";
		}

		int doctorId = ((Doctor) doctor).getId();

		List<Registration> registrations = registrationService.getRegistrationByDoctorIdAndIsNotActive(doctorId);

		model.addAttribute("registrations", registrations);

		return "redirect:/doctor/quan-ly-dat-lich";
	}
}
