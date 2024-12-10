package com.example.bookingcare.controller.user;

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
import com.example.bookingcare.model.Schedule;
import com.example.bookingcare.service.doctor.ClinicService;
import com.example.bookingcare.service.doctor.DoctorService;
import com.example.bookingcare.service.doctor.ScheduleService;

@Controller
public class DoctorDetailController {
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ScheduleService scheduleSevice;

	@Autowired
	private ClinicService clinicService;

	@GetMapping("/danh-sach/bac-sy/{id}")
	public String getSpecialtyDetail(@PathVariable("id") int id, Model model) throws SQLException {

		Doctor doctor = doctorService.getDoctorById(id);
		System.out.print(doctor);
		model.addAttribute("doctor", doctor);

		Clinic clinic = clinicService.getClinicById(doctor.getClinicId());
		System.out.print(clinic);
		model.addAttribute("clinic", clinic);

		LocalDate today = LocalDate.now();
		List<String> nextDays = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			nextDays.add(today.plusDays(i).toString());
		}

		model.addAttribute("nextDays", nextDays);
		return "user/doctorDetail";
	}

	@PostMapping("/danh-sach/bac-sy/{id}")
	public String choseScheduleByDate(@PathVariable("id") int id,
			@RequestParam(value = "date", required = false) String date, Model model) throws SQLException {

		Doctor doctor = doctorService.getDoctorById(id);
		System.out.print(doctor);
		model.addAttribute("doctor", doctor);

		Clinic clinic = clinicService.getClinicById(doctor.getClinicId());
		System.out.print(clinic);
		model.addAttribute("clinic", clinic);

		LocalDate today = LocalDate.now();
		List<String> nextDays = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			nextDays.add(today.plusDays(i).toString());
		}

		model.addAttribute("nextDays", nextDays);

		int doctorId = id;
		Date selectedDate = Date.valueOf(date);

		List<Schedule> schedules = scheduleSevice.getActiveScheduleForDoctor(doctorId, selectedDate);

		System.out.println("Check trang thai :" + schedules.isEmpty());

		System.out.println("Check schedules: " + schedules.toString());
		model.addAttribute("selectedDate", date);
		model.addAttribute("schedules", schedules);

		return "user/doctorDetail";
	}

}