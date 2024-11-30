package com.example.bookingcare.main;

import java.sql.Date;
import java.sql.SQLException;

import com.example.bookingcare.service.doctor.ScheduleService;
import com.example.bookingcare.service.doctor.ScheduleServiceImpl;

public class testSchedule {

	private static final ScheduleService scheduleService = new ScheduleServiceImpl();

	public static void main(String[] args) {
		// Test activate timeslot
//		testActivateTimeslot(1, 1001, "2024-12-01");

		// Test deactivate timeslot
		testDeactivateTimeslot(3, 1, "2024-30-11");
	}

	private static void testActivateTimeslot(int timeslotId, int doctorId, String date) {
		try {
			Date scheduleDate = Date.valueOf(date); // Chuyển đổi từ String sang Date
			scheduleService.activeSchedule(timeslotId, doctorId, scheduleDate);
			System.out.println(
					"Timeslot " + timeslotId + " đã được kích hoạt cho bác sĩ ID " + doctorId + " vào ngày " + date);
		} catch (SQLException e) {
			System.err.println("Lỗi khi kích hoạt timeslot: " + e.getMessage());
		}
	}

	private static void testDeactivateTimeslot(int timeslotId, int doctorId, String date) {
		try {
			if (date == null || date.isEmpty()) {
				System.err.println("Ngày tháng không được để trống.");
				return;
			}

			Date scheduleDate = Date.valueOf(date); // Chuyển đổi từ String sang Date
			scheduleService.deactiveSchedule(timeslotId, doctorId, scheduleDate);
			System.out
					.println("Timeslot " + timeslotId + " đã được tắt cho bác sĩ ID " + doctorId + " vào ngày " + date);
		} catch (IllegalArgumentException e) {
			System.err.println("Lỗi định dạng ngày tháng: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Lỗi khi tắt timeslot: " + e.getMessage());
		}
	}

}
