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
public class ConfirmRegController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private EmailService emailService;

	@PostMapping("/doctor/confirm-reg")
	public String confirmRegistration(@RequestParam("registrationId") int registrationId, Model model) {

		try {
			registrationService.isActive(registrationId);
			System.out.println("Đã xác nhận thành công!");
			model.addAttribute("message", "Đã xác nhận đặt lịch thành công");

			// gửi mail
			Registration registration = registrationService.getRegistrationById(registrationId);
			Doctor doctor = doctorService.getDoctorById(registration.getDoctorId());

			if (registration != null) {
				String userEmail = registration.getEmail();
				String emailContent = "Chào " + registration.getFullName() + ",\n\n"
						+ "Đơn đặt lịch khám của bạn đã được xác nhận thành công.\n" + "Thời gian khám: "
						+ registration.getTimeslotName() + "\n" + "Ngày: " + registration.getDay() + "\n" + "Địa điểm: "
						+ registration.getClinicAddress() + "\n" + "Tên bác sĩ: " + doctor.getFullname() + "\n"
						+ "Mã đăng ký: " + registration.getId();

				// Gọi service để gửi email xác nhận
				emailService.sendConfirmationEmail(userEmail, "Xác nhận đặt lịch khám thành công", emailContent);

				System.out.println("Email đã gửi đi.");
			}
		} catch (Exception e) {
			model.addAttribute("message", "Có lỗi xảy ra khi xác nhận đặt lịch");
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
