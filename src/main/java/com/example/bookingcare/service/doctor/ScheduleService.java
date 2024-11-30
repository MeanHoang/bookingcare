package com.example.bookingcare.service.doctor;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.example.bookingcare.model.Schedule;
import com.example.bookingcare.model.Timeslot;

public interface ScheduleService {

	List<Schedule> getScheduleByDoctorId(int doctorId);

	void addScheduleForDoctor(int doctorId, Date day) throws SQLException;

	void activeSchedule(int timeslotId, int doctorId, Date day) throws SQLException;

	void deactiveSchedule(int timeslotId, int doctorId, Date day) throws SQLException;

	void deleteSchedule(Schedule schedule) throws SQLException;

	public List<Timeslot> getActiveScheduleForDoctor(int doctorId, Date day);

	public List<Timeslot> getAllScheduleForDoctor(int doctorId, Date day);
}
