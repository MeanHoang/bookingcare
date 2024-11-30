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
import com.example.bookingcare.model.Timeslot;
import com.example.bookingcare.service.doctor.ScheduleService;

@Controller
@SessionAttributes("doctor")
public class ManageTimeslotController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping("/doctor/quan-ly-lich-trinh")
	public String manageScheduleByDate(@RequestParam(value = "date", required = false) String date, Model model)
			throws SQLException {
		Object doctor = model.getAttribute("doctor");
		if (doctor == null) {
			return "redirect:/doctor/login";
		}

		LocalDate today = LocalDate.now();
		List<String> nextDays = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			nextDays.add(today.plusDays(i).toString());
		}

		model.addAttribute("nextDays", nextDays);

		int doctorId = ((Doctor) doctor).getId();
		Date selectedDate = Date.valueOf(date);

		List<Timeslot> timeslots = scheduleService.getAllScheduleForDoctor(doctorId, selectedDate);

		System.out.println("Check trang thai :" + timeslots.isEmpty());
		if (timeslots.isEmpty()) {
			scheduleService.addScheduleForDoctor(doctorId, selectedDate);
			model.addAttribute("message", "Lịch trình mới đã được tạo cho ngày " + date);
			// Lấy lại danh sách timeslots sau khi thêm
			timeslots = scheduleService.getAllScheduleForDoctor(doctorId, selectedDate);
		} else {
			model.addAttribute("message", "Lịch trình đã tồn tại cho ngày " + date);
		}

		System.out.println("Check timslots: " + timeslots.toString());
		model.addAttribute("selectedDate", date);
		model.addAttribute("timeslots", timeslots);

		return "doctor/manageSchedule";
	}

	@PostMapping("/doctor/active-schedule")
	public String activateSchedule(@RequestParam("timeslotId") int timeslotId,
			@RequestParam("selectedDate") String selectedDate, Model model) throws SQLException {

		Object doctor = model.getAttribute("doctor");
		if (doctor == null) {
			System.out.println("Doctor session is null, redirecting to login.");
			return "redirect:/doctor/login";
		}

		int doctorId = ((Doctor) doctor).getId();
		System.out.println("Activating timeslot " + timeslotId + " for doctor ID " + doctorId);

		LocalDate selectedLocalDate = LocalDate.parse(selectedDate);
		scheduleService.activeSchedule(timeslotId, doctorId, Date.valueOf(selectedLocalDate));
		model.addAttribute("message", "Timeslot " + timeslotId + " đã được bật.");

		System.out.println("Timeslot " + timeslotId + " activated successfully.");
		return "redirect:/doctor/quan-ly-lich-trinh";
	}

	@PostMapping("/doctor/deactive-schedule")
	public String deactivateSchedule(@RequestParam("timeslotId") int timeslotId,
			@RequestParam("selectedDate") String selectedDate, Model model) throws SQLException {

		Object doctor = model.getAttribute("doctor");
		if (doctor == null) {
			System.out.println("Doctor session is null, redirecting to login.");
			return "redirect:/doctor/login";
		}

		int doctorId = ((Doctor) doctor).getId();

		LocalDate selectedLocalDate = LocalDate.parse(selectedDate);

		System.out.println(
				"start deactive doctorId: " + doctorId + ", timeslotId: " + timeslotId + ", day: " + selectedDate);

		scheduleService.deactiveSchedule(timeslotId, doctorId, Date.valueOf(selectedLocalDate));
		model.addAttribute("message", "Timeslot " + timeslotId + " đã được tắt.");

		System.out.println("Timeslot " + timeslotId + " deactivated successfully.");
		model.addAttribute("selectedDate", selectedLocalDate);
		List<Timeslot> timeslots = scheduleService.getAllScheduleForDoctor(doctorId, Date.valueOf(selectedLocalDate));

		model.addAttribute("timeslots", timeslots);
		return "redirect:/doctor/quan-ly-lich-trinh";
	}

}
