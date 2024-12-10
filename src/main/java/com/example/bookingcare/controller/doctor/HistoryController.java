package com.example.bookingcare.controller.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Registration;
import com.example.bookingcare.service.doctor.ScheduleService;
import com.example.bookingcare.service.user.RegistrationService;

@Controller
@SessionAttributes("doctor")
public class HistoryController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private RegistrationService registrationService;

	@GetMapping("/doctor/lich-su-kham-benh")
	public String historyRegistration(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int size, Model model) {

		Object doctor = model.getAttribute("doctor");
		if (doctor == null) {
			return "redirect:/doctor/login";
		}

		int doctorId = ((Doctor) doctor).getId();

		// Fetch paginated registrations
		List<Registration> registrations = registrationService.getRegistrationByDoctorIdAndIsActive(doctorId, page,
				size);

		// Optionally, fetch total count for pagination controls
		int totalCount = registrationService.countRegistrationByDoctorIdAndIsNotActive(doctorId);
		System.out.println("Tong so don dat: " + totalCount);

		System.out.println("Check don dat: " + registrations);

		// Calculate total pages
		int totalPages = (int) Math.ceil((double) totalCount / size);

		model.addAttribute("registrations", registrations);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("pageSize", size);

		return "doctor/historyRegistration";
	}

}
