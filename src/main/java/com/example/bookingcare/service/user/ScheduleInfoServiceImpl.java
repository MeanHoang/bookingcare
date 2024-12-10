package com.example.bookingcare.service.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookingcare.connection.ConnectionPoolImpl;
import com.example.bookingcare.model.ScheduleInfo;

@Service
public class ScheduleInfoServiceImpl implements ScheduleInfoService {

	private ConnectionPoolImpl connectionPool;

	public ScheduleInfoServiceImpl() {
		this.connectionPool = new ConnectionPoolImpl(); // Khởi tạo connection pool
	}

	@Override
	public List<ScheduleInfo> getScheduleInfoByDoctorAndDay(int doctorId, Date day) {
		List<ScheduleInfo> scheduleInfoList = new ArrayList<>();
		String query = """
				    SELECT
				        s.day AS schedule_day,
				        ts.name AS timeslot_time,
				        r.fullname AS patient_name,
				        r.phone_number AS patient_phone,
				        r.email AS patient_email,
				        r.gender AS patient_gender,
				        r.note AS patient_note,
				        r.id AS registration_id

				    FROM
				        schedule s
				    LEFT JOIN
				        timeslot ts ON s.timeslot_id = ts.id
				    LEFT JOIN
				        registration r ON s.id = r.schedule_id AND r.is_active = TRUE
				    WHERE
				        s.day = ?
				        AND s.doctor_id = ?
				        AND s.is_active = TRUE;
				""";

		try (Connection connection = connectionPool.getConnection("ScheduleInfoService");
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setDate(1, new java.sql.Date(day.getTime()));
			preparedStatement.setInt(2, doctorId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					ScheduleInfo scheduleInfo = new ScheduleInfo();
					scheduleInfo.setScheduleDay(resultSet.getString("schedule_day"));
					scheduleInfo.setTimeslotTime(resultSet.getString("timeslot_time"));
					scheduleInfo.setPatientName(resultSet.getString("patient_name"));
					scheduleInfo.setPatientPhone(resultSet.getString("patient_phone"));
					scheduleInfo.setPatientEmail(resultSet.getString("patient_email"));
					scheduleInfo.setPatientGender(resultSet.getString("patient_gender"));
					scheduleInfo.setPatientNote(resultSet.getString("patient_note"));
					scheduleInfo.setRegistrationId(resultSet.getInt("registration_id"));

					scheduleInfoList.add(scheduleInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return scheduleInfoList;
	}

}
