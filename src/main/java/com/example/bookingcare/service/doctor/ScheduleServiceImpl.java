package com.example.bookingcare.service.doctor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookingcare.connection.ConnectionPoolImpl;
import com.example.bookingcare.model.Schedule;
import com.example.bookingcare.model.Timeslot;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	private final ConnectionPoolImpl connectionPool;

	public ScheduleServiceImpl() {
		this.connectionPool = new ConnectionPoolImpl();
	}

	@Override
	public List<Schedule> getScheduleByDoctorId(int doctorId) {
		List<Schedule> schedules = new ArrayList<>();
		String query = "SELECT id, doctor_id, day, timeslot_id, is_active " + "FROM schedule "
				+ "WHERE doctor_id = ? AND is_active = TRUE"; // Lọc theo doctorId và trạng thái hoạt động

		try (Connection connection = connectionPool.getConnection("Schedule");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, doctorId);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Schedule schedule = new Schedule();
					schedule.setId(rs.getInt("id"));
					schedule.setDoctorId(rs.getInt("doctor_id"));
					schedule.setDay(rs.getDate("day"));
					schedule.setTimeslotId(rs.getInt("timeslot_id"));
					schedule.setActive(rs.getBoolean("is_active"));

					schedules.add(schedule);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace(); // In lỗi nếu có vấn đề với cơ sở dữ liệu
		}

		return schedules;
	}

	@Override
	public void addScheduleForDoctor(int doctorId, Date day) throws SQLException {
		String query = "INSERT INTO schedule (doctor_id, day, timeslot_id, is_active) " + "SELECT ?, ?, ts.id, TRUE "
				+ "FROM timeslot ts WHERE ts.id BETWEEN 1 AND 18"; // Thêm tất cả các timeslot từ 1 đến 18

		try (Connection connection = connectionPool.getConnection("Schedule");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, doctorId); // Set doctor_id
			stmt.setDate(2, day); // Set day

			stmt.executeUpdate(); // Thực thi lệnh thêm vào bảng schedule

		} catch (SQLException e) {
			throw new SQLException("Error adding schedule for doctor", e);
		}
	}

	@Override
	public void deleteSchedule(Schedule schedule) throws SQLException {
		String query = "DELETE FROM schedule WHERE id = ?";

		try (Connection connection = connectionPool.getConnection("Schedule");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, schedule.getId());
			stmt.executeUpdate(); // Xóa lịch làm việc khỏi bảng schedule

		} catch (SQLException e) {
			throw new SQLException("Error deleting schedule", e);
		}
	}

	@Override
	public List<Schedule> getActiveScheduleForDoctor(int doctorId, Date day) {
		List<Schedule> schedules = new ArrayList<>();
		String query = "SELECT s.id, s.doctor_id, s.day, s.timeslot_id, s.is_active, s.is_used, t.name "
				+ "FROM schedule s " + "JOIN timeslot t ON s.timeslot_id = t.id "
				+ "WHERE s.is_active = TRUE AND s.doctor_id = ? AND s.day = ? AND s.is_used= FALSE";

		try (Connection connection = connectionPool.getConnection("Schedule");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			// Gán giá trị cho tham số trong truy vấn
			stmt.setInt(1, doctorId); // doctorId
			stmt.setDate(2, day); // day (selectedDate)

			System.out.println("Query prepared: " + stmt);

			try (ResultSet rs = stmt.executeQuery()) {
				System.out.println("Query executed successfully.");

				while (rs.next()) { // Chỉnh lại để duyệt tất cả kết quả
					Schedule schedule = new Schedule();
					schedule.setId(rs.getInt("id"));
					schedule.setDoctorId(rs.getInt("doctor_id"));
					schedule.setDay(rs.getDate("day"));
					schedule.setTimeslotId(rs.getInt("timeslot_id"));
					schedule.setActive(rs.getBoolean("is_active"));
					schedule.setUsed(rs.getBoolean("is_used"));
					schedule.setName(rs.getString("name"));

					schedules.add(schedule);

					// Debug thông tin bản ghi
					System.out.println("Check Schedule: " + schedule);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Error: " + e.getMessage());
		}

		return schedules;
	}

	@Override
	public List<Timeslot> getAllScheduleForDoctor(int doctorId, Date day) {
		List<Timeslot> timeslots = new ArrayList<>();
		String query = "SELECT t.id, t.name, t.start_time, t.end_time, s.is_active " + "FROM schedule s "
				+ "JOIN timeslot t ON s.timeslot_id = t.id " + "WHERE s.doctor_id = ? " + "AND s.day = ? ";
		try (Connection connection = connectionPool.getConnection("Schedule");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, doctorId);
			stmt.setDate(2, day);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Timeslot timeslot = new Timeslot();
					timeslot.setId(rs.getInt("id"));
					timeslot.setName(rs.getString("name"));
					timeslot.setStartTime(rs.getString("start_time"));
					timeslot.setEndTime(rs.getString("end_time"));
					timeslot.setIsActive(rs.getBoolean("is_active"));
					timeslots.add(timeslot);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return timeslots;
	}

	@Override
	public void deactiveSchedule(int timeslotId, int doctorId, Date day) throws SQLException {
		String query = "UPDATE schedule " + "SET is_active = FALSE "
				+ "WHERE doctor_id = ? AND timeslot_id = ? AND day = ?";

		try (Connection connection = connectionPool.getConnection("Schedule");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, doctorId);
			stmt.setInt(2, timeslotId);
			stmt.setDate(3, day);

			int rowsUpdated = stmt.executeUpdate();
			System.out.println("Rows updated: " + rowsUpdated);

			if (rowsUpdated > 0) {
				System.out.println("Deactivated schedule for doctor ID " + doctorId + ", timeslot ID " + timeslotId
						+ ", on " + day + ".");
			} else {
				System.out.println("No schedule found for doctor ID " + doctorId + ", timeslot ID " + timeslotId
						+ ", on " + day + ".");
			}
		} catch (SQLException e) {
			throw new SQLException("Error deactivating schedule for doctor ID: " + doctorId, e);
		}
	}

	@Override
	public void activeSchedule(int timeslotId, int doctorId, Date day) throws SQLException {
		String query = "UPDATE schedule " + "SET is_active = TRUE "
				+ "WHERE doctor_id = ? AND timeslot_id = ? AND day = ?";

		try (Connection connection = connectionPool.getConnection("Schedule");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, doctorId);
			stmt.setInt(2, timeslotId);
			stmt.setDate(3, day);

			int rowsUpdated = stmt.executeUpdate(); // Thực thi câu lệnh UPDATE

			if (rowsUpdated > 0) {
				System.out.println("Activated schedule for doctor ID " + doctorId + ", timeslot ID " + timeslotId
						+ ", on " + day + ".");
			} else {
				System.out.println("No schedule found for doctor ID " + doctorId + ", timeslot ID " + timeslotId
						+ ", on " + day + ".");
			}
		} catch (SQLException e) {
			throw new SQLException("Error activating schedule for doctor ID: " + doctorId, e);
		}
	}

	@Override
	public void isUsedSchedule(int scheduleId) throws SQLException {
		// SQL query to mark the schedule as used
		String query = "UPDATE schedule SET is_used = TRUE WHERE id = ?";

		try (Connection connection = connectionPool.getConnection("Schedule");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, scheduleId); // Set the schedule ID parameter

			int rowsUpdated = stmt.executeUpdate(); // Execute the update

			if (rowsUpdated > 0) {
				System.out.println("Schedule ID " + scheduleId + " has been marked as used.");
			} else {
				System.out.println("No schedule found with ID " + scheduleId + ".");
			}
		} catch (SQLException e) {
			// Handle any SQL exception that occurs
			throw new SQLException("Error marking schedule as used for schedule ID: " + scheduleId, e);
		}
	}

	@Override
	public void isNotUsedSchedule(int scheduleId) throws SQLException {
		// SQL query to mark the schedule as used
		String query = "UPDATE schedule SET is_used = FALSE WHERE id = ?";

		try (Connection connection = connectionPool.getConnection("Schedule");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, scheduleId); // Set the schedule ID parameter

			int rowsUpdated = stmt.executeUpdate(); // Execute the update

			if (rowsUpdated > 0) {
				System.out.println("Schedule ID " + scheduleId + " has been marked as used.");
			} else {
				System.out.println("No schedule found with ID " + scheduleId + ".");
			}
		} catch (SQLException e) {
			// Handle any SQL exception that occurs
			throw new SQLException("Error marking schedule as used for schedule ID: " + scheduleId, e);
		}
	}

	@Override
	public Schedule getScheduleByID(int scheduleId) {
		Schedule schedule = null;
		String query = "SELECT id, doctor_id, day, timeslot_id, is_active, is_used " + "FROM schedule WHERE id = ?";

		try (Connection connection = connectionPool.getConnection("Schedule");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, scheduleId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					schedule = new Schedule();
					schedule.setId(rs.getInt("id"));
					schedule.setDoctorId(rs.getInt("doctor_id"));
					schedule.setDay(rs.getDate("day"));
					schedule.setTimeslotId(rs.getInt("timeslot_id"));
					schedule.setActive(rs.getBoolean("is_active"));
					schedule.setUsed(rs.getBoolean("is_used"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error retrieving schedule by ID: " + e.getMessage());
		}

		return schedule;
	}

}
