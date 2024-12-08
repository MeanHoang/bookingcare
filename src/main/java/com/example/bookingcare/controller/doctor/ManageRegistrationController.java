package com.example.bookingcare.controller.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Registration;
import com.example.bookingcare.service.doctor.ScheduleService;
import com.example.bookingcare.service.user.RegistrationService;

@Controller
@SessionAttributes("doctor")
public class ManageRegistrationController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private RegistrationService registrationService;

	@GetMapping("/doctor/quan-ly-dat-lich")
	public String manageRegistration(Model model) {

		Object doctor = model.getAttribute("doctor");
		if (doctor == null) {
			return "redirect:/doctor/login";
		}

		int doctorId = ((Doctor) doctor).getId();

		List<Registration> registrations = registrationService.getRegistrationByDoctorIdAndIsNotActive(doctorId);

		model.addAttribute("registrations", registrations);
		return "doctor/manageRegistration";
	}
}
