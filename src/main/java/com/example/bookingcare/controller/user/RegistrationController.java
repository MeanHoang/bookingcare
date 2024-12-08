package com.example.bookingcare.controller.user;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Registration;
import com.example.bookingcare.model.Schedule;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.ScheduleServiceImpl;
import com.example.bookingcare.service.user.RegistrationServiceImpl;

@Controller
public class RegistrationController {

	@Autowired
	private RegistrationServiceImpl registrationService;

	@Autowired
	private DoctorServiceImpl doctorService;

	@Autowired
	private ClinicServiceImpl clinicService;

	@Autowired
	private ScheduleServiceImpl scheduleService;

	@GetMapping("/dat-lich-kham")
	public String showRegistrationForm(@RequestParam(value = "doctorId", required = false) Integer doctorId,
			@RequestParam(value = "scheduleId", required = false) Integer scheduleId,
			@RequestParam(value = "timeslotName", required = false) String timeslotName,
			@RequestParam(value = "day", required = false) String day, Model model) {

		// Check for missing or invalid parameters
		if (doctorId == null || scheduleId == null || timeslotName == null || day == null) {
			return "redirect:/"; // Redirect to home if any parameter is missing
		}

		// Validate and convert day to Date
		Date scheduleDay = Date.valueOf(day);

		Doctor doctor = doctorService.getDoctorById(doctorId);
		if (doctor == null) {
			return "redirect:/"; // Redirect if doctor is not found
		}

		Clinic clinic = clinicService.getClinicById(doctor.getClinicId());
		if (clinic == null) {
			return "redirect:/"; // Redirect if clinic is not found
		}

		Schedule schedule = scheduleService.getScheduleByID(scheduleId);
		if (schedule == null) {
			return "redirect:/"; // Redirect if doctor is not found
		}

		// Create Registration object
		Registration registration = new Registration();
		registration.setDoctorId(doctorId);
		registration.setTimeslotName(timeslotName);
		registration.setScheduleId(scheduleId);
		registration.setDay(scheduleDay); // Use the validated Date object
		registration.setActive(true);
		registration.setClinicAddress(clinic.getAddress());

		System.out.println("Check doctor: " + doctor.toString());
		System.out.println("Check clinic: " + clinic.toString());
		System.out.println("Check selectedDate: " + day);
		System.out.println("Check registration: " + registration.toString());
		System.out.println("Check schedule: " + schedule.toString());

		model.addAttribute("doctor", doctor);
		model.addAttribute("clinic", clinic);
		model.addAttribute("selectedDate", day);
		model.addAttribute("registration", registration);
		model.addAttribute("schedule", schedule);

		return "user/registrationForm";
	}
}
