package com.example.bookingcare.service.user;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.example.bookingcare.model.Registration;

public interface RegistrationService {

	List<Registration> getAllRegistration();

	void addRegistration(Registration registration) throws SQLException;

	List<Registration> getRegistrationByDoctorId(int doctorId);

	List<Registration> getRegistrationByDoctorIdAndDay(int doctorId, Date day);

	List<Registration> getRegistrationByDoctorIdAndIsNotActive(int doctorId);

	Registration getRegistrationByScheduleId(int schedule);

	void isActive(int registrationId) throws SQLException;

	void deleteRegistration(int registrationId) throws SQLException;

	Registration getRegistrationById(int registrationId);

	int CountRegistrationByMonth(int month);

	List<Registration> getRegistrationByDoctorIdAndIsNotActive(int doctorId, int page, int size);

	int countRegistrationByDoctorIdAndIsNotActive(int doctorId);

}
