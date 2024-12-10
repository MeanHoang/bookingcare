package com.example.bookingcare.controller.doctor;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Registration;
import com.example.bookingcare.model.Schedule;
import com.example.bookingcare.service.doctor.ClinicService;
import com.example.bookingcare.service.doctor.DoctorService;
import com.example.bookingcare.service.doctor.ScheduleService;
import com.example.bookingcare.service.user.RegistrationService;

@Controller
public class RegistrationDetailController {
	@Autowired
	private RegistrationService registrationService;

	@GetMapping("/doctor/thong-tin-don-dat/{id}")
	public String getSpecialtyDetail(@PathVariable("id") int id, Model model) throws SQLException {

		Registration registration = registrationService.getRegistrationById(id);
		System.out.print(registration);
		model.addAttribute("registration", registration);

		return "doctor/registrationDetail";
	}

	
}