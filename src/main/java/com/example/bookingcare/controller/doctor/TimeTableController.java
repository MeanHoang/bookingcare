package com.example.bookingcare.controller.doctor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bookingcare.service.user.ScheduleInfoService;

@Controller
@SessionAttributes("doctor")
public class TimeTableController {

	@Autowired
	private ScheduleInfoService scheduleInfoService;

	@GetMapping("/doctor/thoi-khoa-bieu")
	public String timeTable(Model model) {
		Object doctor = model.getAttribute("doctor");
		if (doctor == null) {
			return "redirect:/doctor/login";
		}

		System.out.println("check doctor:" + doctor.toString());

		LocalDate today = LocalDate.now();
		List<String> nextDays = new ArrayList<>();
		// Thêm ngày hôm nay
		nextDays.add(today.toString());
		for (int i = 1; i <= 3; i++) {
			nextDays.add(today.plusDays(i).toString());
		}

		model.addAttribute("nextDays", nextDays);
		return "doctor/timeTable";
	}
}
