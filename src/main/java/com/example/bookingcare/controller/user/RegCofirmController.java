package com.example.bookingcare.controller.user;

import java.sql.Date;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Đúng import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Registration;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.ScheduleServiceImpl;
import com.example.bookingcare.service.user.RegistrationServiceImpl;

@Controller
public class RegCofirmController {

	@Autowired
	private RegistrationServiceImpl registrationService;

	@Autowired
	private ScheduleServiceImpl scheduleService;

	@Autowired
	private ClinicServiceImpl clinicService;

	@Autowired
	private DoctorServiceImpl doctorService;

	@GetMapping("/dat-lich-kham-thanh-cong")
	public String confirmRegistration(@RequestParam("fullname") String fullname, @RequestParam("gender") String gender,
			@RequestParam("birthday") Date birthday, @RequestParam("phonenumber") String phonenumber,
			@RequestParam("email") String email, @RequestParam("province") String province,
			@RequestParam("district") String district, @RequestParam("commune") String commune,
			@RequestParam(value = "note", required = false) String note, @RequestParam("doctorId") Integer doctorId,
			@RequestParam("scheduleId") Integer scheduleId, @RequestParam("selectedDay") Date selectedDay,
			@RequestParam("timeslotName") String timeslotName, Model model) throws SQLException {

		Registration registration = new Registration();
		Clinic clinic = clinicService.getClinicById(doctorId);

		registration.setDoctorId(doctorId);
		registration.setScheduleId(scheduleId);
		registration.setTimeslotName(timeslotName);
		registration.setClinicAddress(clinic.getAddress());
		registration.setDay(selectedDay);
		registration.setFullName(fullname);
		registration.setGender(gender);
		registration.setBirthday(birthday);
		registration.setPhoneNumber(phonenumber);
		registration.setEmail(email);
		registration.setProvince(province);
		registration.setDistrict(district);
		registration.setCommune(commune);
		registration.setNote(note);

		System.out.println("check Registration: " + registration);
		registrationService.addRegistration(registration);

		scheduleService.isUsedSchedule(scheduleId);

		if (registration != null) {
			model.addAttribute("registration", registration);
		}

		Doctor doctor = doctorService.getDoctorById(doctorId);
		System.out.println("check doctor: " + doctor);
		System.out.println("check clinic: " + clinic);

		model.addAttribute("registration", registration);
		model.addAttribute("clinic", clinic);
		model.addAttribute("doctor", doctor);

		return "user/bookingSuccess";
	}
}
