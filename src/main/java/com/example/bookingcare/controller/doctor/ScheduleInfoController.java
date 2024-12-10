package com.example.bookingcare.controller.doctor;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.ScheduleInfo;
import com.example.bookingcare.service.user.ScheduleInfoService;

@Controller
@SessionAttributes("doctor")
public class ScheduleInfoController {

	@Autowired
	private ScheduleInfoService scheduleInfoService;

	@PostMapping("/doctor/thoi-khoa-bieu")
	public String manageScheduleByDate(@RequestParam(value = "date", required = false) String date, Model model)
			throws SQLException {
		Object doctor = model.getAttribute("doctor");
		if (doctor == null) {
			return "redirect:/doctor/login";
		}

		LocalDate today = LocalDate.now();
		List<String> nextDays = new ArrayList<>();
		// Thêm ngày hôm nay
		nextDays.add(today.toString());
		for (int i = 1; i <= 3; i++) {
			nextDays.add(today.plusDays(i).toString());
		}

		model.addAttribute("nextDays", nextDays);

		int doctorId = ((Doctor) doctor).getId();
		Date selectedDate = Date.valueOf(date);

		List<ScheduleInfo> scheduleInfos = scheduleInfoService.getScheduleInfoByDoctorAndDay(doctorId, selectedDate);

		System.out.println("check scheduleInfos: " + scheduleInfos.toString());

		model.addAttribute("selectedDate", date);
		model.addAttribute("scheduleInfos", scheduleInfos);

		return "doctor/timeTable";
	}

}
