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
import com.example.bookingcare.model.Registration;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private ConnectionPoolImpl connectionPool;

	public RegistrationServiceImpl() {
		this.connectionPool = new ConnectionPoolImpl(); // Khởi tạo connection pool
	}

	@Override
	public List<Registration> getAllRegistration() {
		List<Registration> registrations = new ArrayList<>();
		String sql = "SELECT * FROM registration";
		System.out.println("Executing SQL: " + sql); // In SQL query ra System

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Registration registration = new Registration();
				registration.setId(resultSet.getInt("id"));
				registration.setDoctorId(resultSet.getInt("doctor_id"));
				registration.setScheduleId(resultSet.getInt("schedule_id"));
				registration.setTimeslotName(resultSet.getString("timeslot_name"));
				registration.setFullName(resultSet.getString("fullname"));
				registration.setDay(resultSet.getDate("day"));
				registration.setGender(resultSet.getString("gender"));
				registration.setBirthday(resultSet.getDate("birthday"));
				registration.setPhoneNumber(resultSet.getString("phone_number"));
				registration.setEmail(resultSet.getString("email"));
				registration.setProvince(resultSet.getString("province"));
				registration.setDistrict(resultSet.getString("district"));
				registration.setCommune(resultSet.getString("commune"));
				registration.setPrice(resultSet.getInt("price"));
				registration.setNote(resultSet.getString("note"));
				registration.setActive(resultSet.getBoolean("is_active"));
				registration.setClinicAddress(resultSet.getString("clinic_address"));
				registrations.add(registration);

				System.out.println("Loaded Registration: " + registration.getFullName());
			}
		} catch (SQLException e) {
			System.out.println("Error while fetching registrations: " + e.getMessage());
			e.printStackTrace();
		}
		return registrations;
	}

	@Override
	public void addRegistration(Registration registration) throws SQLException {
		// Cập nhật câu lệnh SQL với các trường mới
		String query = "INSERT INTO registration (doctor_id, schedule_id, timeslot_name, clinic_address, fullname, day, gender, birthday, phone_number, email, province, district, commune, price, note) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		System.out.println("Executing SQL: " + query);
		System.out.println("Adding Registration: " + registration.getFullName());

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			// Gán các tham số vào PreparedStatement theo đúng thứ tự
			stmt.setInt(1, registration.getDoctorId()); // doctor_id
			stmt.setInt(2, registration.getScheduleId()); // schedule_id
			stmt.setString(3, registration.getTimeslotName()); // timeslot_name
			stmt.setString(4, registration.getClinicAddress()); // clinic_address
			stmt.setString(5, registration.getFullName()); // fullname
			stmt.setDate(6, (Date) registration.getDay()); // day
			stmt.setString(7, registration.getGender()); // gender
			stmt.setDate(8, (Date) registration.getBirthday()); // birthday
			stmt.setString(9, registration.getPhoneNumber()); // phone_number
			stmt.setString(10, registration.getEmail()); // email
			stmt.setString(11, registration.getProvince()); // province
			stmt.setString(12, registration.getDistrict()); // district
			stmt.setString(13, registration.getCommune()); // commune
			stmt.setInt(14, registration.getPrice()); // price
			stmt.setString(15, registration.getNote()); // note

			// Thực thi câu lệnh
			stmt.executeUpdate();
			System.out.println("Registration added successfully.");
		} catch (SQLException e) {
			System.out.println("Error while adding registration: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public List<Registration> getRegistrationByDoctorId(int doctorId) {
		List<Registration> registrations = new ArrayList<>();
		String sql = "SELECT * FROM registration WHERE doctor_id = ?";
		System.out.println("Executing SQL: " + sql); // In SQL query ra System

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, doctorId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Registration registration = new Registration();
				registration.setId(resultSet.getInt("id"));
				registration.setDoctorId(resultSet.getInt("doctor_id"));
				registration.setScheduleId(resultSet.getInt("schedule_id"));
				registration.setTimeslotName(resultSet.getString("timeslot_name"));
				registration.setFullName(resultSet.getString("fullname"));
				registration.setDay(resultSet.getDate("day"));
				registration.setGender(resultSet.getString("gender"));
				registration.setBirthday(resultSet.getDate("birthday"));
				registration.setPhoneNumber(resultSet.getString("phone_number"));
				registration.setEmail(resultSet.getString("email"));
				registration.setProvince(resultSet.getString("province"));
				registration.setDistrict(resultSet.getString("district"));
				registration.setCommune(resultSet.getString("commune"));
				registration.setPrice(resultSet.getInt("price"));
				registration.setNote(resultSet.getString("note"));
				registration.setActive(resultSet.getBoolean("is_active"));
				registration.setClinicAddress(resultSet.getString("clinic_address"));

				registrations.add(registration);
				System.out.println("Loaded Registration for Doctor ID: " + doctorId);
			}
		} catch (SQLException e) {
			System.out.println("Error while fetching registrations for Doctor ID: " + e.getMessage());
			e.printStackTrace();
		}
		return registrations;
	}

	@Override
	public List<Registration> getRegistrationByDoctorIdAndDay(int doctorId, Date day) {
		List<Registration> registrations = new ArrayList<>();
		String sql = "SELECT * FROM registration WHERE doctor_id = ? AND day = ?";
		System.out.println("Executing SQL: " + sql); // In SQL query ra System

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, doctorId);
			preparedStatement.setDate(2, day);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Registration registration = new Registration();
				registration.setId(resultSet.getInt("id"));
				registration.setDoctorId(resultSet.getInt("doctor_id"));
				registration.setScheduleId(resultSet.getInt("schedule_id"));
				registration.setTimeslotName(resultSet.getString("timeslot_name"));
				registration.setFullName(resultSet.getString("fullname"));
				registration.setDay(resultSet.getDate("day"));
				registration.setGender(resultSet.getString("gender"));
				registration.setBirthday(resultSet.getDate("birthday"));
				registration.setPhoneNumber(resultSet.getString("phone_number"));
				registration.setEmail(resultSet.getString("email"));
				registration.setProvince(resultSet.getString("province"));
				registration.setDistrict(resultSet.getString("district"));
				registration.setCommune(resultSet.getString("commune"));
				registration.setPrice(resultSet.getInt("price"));
				registration.setNote(resultSet.getString("note"));
				registration.setActive(resultSet.getBoolean("is_active"));
				registration.setClinicAddress(resultSet.getString("clinic_address"));

				registrations.add(registration);
				System.out.println("Loaded Registration for Doctor ID: " + doctorId + " on Day: " + day);
			}
		} catch (SQLException e) {
			System.out.println("Error while fetching registrations for Doctor ID and Day: " + e.getMessage());
			e.printStackTrace();
		}
		return registrations;
	}

	@Override
	public Registration getRegistrationByScheduleId(int schedule) {
		String sql = "SELECT * FROM registration WHERE schedule_id = ?";
		System.out.println("Executing SQL: " + sql); // In SQL query ra System

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, schedule);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Registration registration = new Registration();
				registration.setId(resultSet.getInt("id"));
				registration.setDoctorId(resultSet.getInt("doctor_id"));
				registration.setScheduleId(resultSet.getInt("schedule_id"));
				registration.setTimeslotName(resultSet.getString("timeslot_name"));
				registration.setFullName(resultSet.getString("fullname"));
				registration.setDay(resultSet.getDate("day"));
				registration.setGender(resultSet.getString("gender"));
				registration.setBirthday(resultSet.getDate("birthday"));
				registration.setPhoneNumber(resultSet.getString("phone_number"));
				registration.setEmail(resultSet.getString("email"));
				registration.setProvince(resultSet.getString("province"));
				registration.setDistrict(resultSet.getString("district"));
				registration.setCommune(resultSet.getString("commune"));
				registration.setPrice(resultSet.getInt("price"));
				registration.setNote(resultSet.getString("note"));
				registration.setActive(resultSet.getBoolean("is_active"));
				registration.setClinicAddress(resultSet.getString("clinic_address"));

				System.out.println("Loaded Registration for Schedule ID: " + schedule);
				return registration;
			}
		} catch (SQLException e) {
			System.out.println("Error while fetching registration for Schedule ID: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Registration> getRegistrationByDoctorIdAndIsNotActive(int doctorId) {
		List<Registration> registrations = new ArrayList<>();
		String sql = "SELECT * FROM registration WHERE doctor_id = ? AND is_active = false AND day >= CURDATE()";
		System.out.println("Executing SQL: " + sql);

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Gán tham số doctorId vào câu lệnh SQL
			preparedStatement.setInt(1, doctorId);

			// Thực thi câu lệnh
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				// Dùng ResultSet để tạo danh sách các Registration hợp lệ
				Registration registration = new Registration();
				registration.setId(resultSet.getInt("id"));
				registration.setDoctorId(resultSet.getInt("doctor_id"));
				registration.setScheduleId(resultSet.getInt("schedule_id"));
				registration.setTimeslotName(resultSet.getString("timeslot_name"));
				registration.setFullName(resultSet.getString("fullname"));
				registration.setDay(resultSet.getDate("day"));
				registration.setGender(resultSet.getString("gender"));
				registration.setBirthday(resultSet.getDate("birthday"));
				registration.setPhoneNumber(resultSet.getString("phone_number"));
				registration.setEmail(resultSet.getString("email"));
				registration.setProvince(resultSet.getString("province"));
				registration.setDistrict(resultSet.getString("district"));
				registration.setCommune(resultSet.getString("commune"));
				registration.setPrice(resultSet.getInt("price"));
				registration.setNote(resultSet.getString("note"));
				registration.setActive(resultSet.getBoolean("is_active"));
				registration.setClinicAddress(resultSet.getString("clinic_address"));

				// Thêm vào danh sách
				registrations.add(registration);
				System.out.println(
						"Loaded Registration for Doctor ID: " + doctorId + " with Date: " + resultSet.getDate("day"));
			}

		} catch (SQLException e) {
			System.out.println("Error while fetching registrations: " + e.getMessage());
			e.printStackTrace();
		}

		return registrations;
	}

	@Override
	public void isActive(int registrationId) throws SQLException {
		String query = "UPDATE registration SET is_active = true WHERE id = ?";
		System.out.println("Executing SQL: " + query);

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, registrationId);

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Successfully activated registration with ID: " + registrationId);
			} else {
				System.out.println("No registration found with ID: " + registrationId);
			}
		} catch (SQLException e) {
			System.out.println("Error while activating registration: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRegistration(int registrationId) throws SQLException {
		String query = "DELETE FROM registration WHERE id = ?";
		System.out.println("Executing SQL: " + query);

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, registrationId);

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Successfully deleted registration with ID: " + registrationId);
			} else {
				System.out.println("No registration found with ID: " + registrationId);
			}
		} catch (SQLException e) {
			System.out.println("Error while deleting registration: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Registration getRegistrationById(int registrationId) {
		String sql = "SELECT * FROM registration WHERE id = ?";
		System.out.println("Executing SQL: " + sql); // In câu lệnh SQL ra console để dễ debug

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, registrationId); // Gán ID vào câu lệnh SQL

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					// Tạo một đối tượng Registration và gán thông tin từ ResultSet
					Registration registration = new Registration();
					registration.setId(resultSet.getInt("id"));
					registration.setDoctorId(resultSet.getInt("doctor_id"));
					registration.setScheduleId(resultSet.getInt("schedule_id"));
					registration.setTimeslotName(resultSet.getString("timeslot_name"));
					registration.setFullName(resultSet.getString("fullname"));
					registration.setDay(resultSet.getDate("day"));
					registration.setGender(resultSet.getString("gender"));
					registration.setBirthday(resultSet.getDate("birthday"));
					registration.setPhoneNumber(resultSet.getString("phone_number"));
					registration.setEmail(resultSet.getString("email"));
					registration.setProvince(resultSet.getString("province"));
					registration.setDistrict(resultSet.getString("district"));
					registration.setCommune(resultSet.getString("commune"));
					registration.setPrice(resultSet.getInt("price"));
					registration.setNote(resultSet.getString("note"));
					registration.setActive(resultSet.getBoolean("is_active"));
					registration.setClinicAddress(resultSet.getString("clinic_address"));

					System.out.println("Loaded Registration: " + registration.getFullName());
					return registration; // Trả về bản ghi nếu tìm thấy
				}
			}
		} catch (SQLException e) {
			System.out.println("Error while fetching registration by ID: " + e.getMessage());
			e.printStackTrace();
		}

		return null; // Nếu không tìm thấy hoặc có lỗi, trả về null
	}

	@Override
	public int CountRegistrationByMonth(int month) {
		String sql = "SELECT COUNT(*) AS count FROM registration WHERE MONTH(day) = ?";
		System.out.println("Executing SQL: " + sql); // In câu lệnh SQL để debug

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Gán tham số tháng vào câu lệnh SQL
			preparedStatement.setInt(1, month);

			// Thực thi câu lệnh và lấy kết quả
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt("count");
				System.out.println("Count of registrations in month " + month + ": " + count);
				return count;
			}
		} catch (SQLException e) {
			System.out.println("Error while counting registrations for month: " + e.getMessage());
			e.printStackTrace();
		}

		// Trả về 0 nếu có lỗi hoặc không tìm thấy kết quả
		return 0;
	}

	@Override
	public List<Registration> getRegistrationByDoctorIdAndIsNotActive(int doctorId, int page, int size) {
		List<Registration> registrations = new ArrayList<>();
		String sql = "SELECT * FROM registration WHERE doctor_id = ? AND is_active = false AND day >= CURDATE() LIMIT ? OFFSET ?";
		System.out.println("Executing SQL: " + sql);

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Gán tham số cho câu lệnh SQL
			preparedStatement.setInt(1, doctorId);
			preparedStatement.setInt(2, size);
			preparedStatement.setInt(3, page * size);

			// Thực thi câu lệnh
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Registration registration = new Registration();
				registration.setId(resultSet.getInt("id"));
				registration.setDoctorId(resultSet.getInt("doctor_id"));
				registration.setScheduleId(resultSet.getInt("schedule_id"));
				registration.setTimeslotName(resultSet.getString("timeslot_name"));
				registration.setFullName(resultSet.getString("fullname"));
				registration.setDay(resultSet.getDate("day"));
				registration.setGender(resultSet.getString("gender"));
				registration.setBirthday(resultSet.getDate("birthday"));
				registration.setPhoneNumber(resultSet.getString("phone_number"));
				registration.setEmail(resultSet.getString("email"));
				registration.setProvince(resultSet.getString("province"));
				registration.setDistrict(resultSet.getString("district"));
				registration.setCommune(resultSet.getString("commune"));
				registration.setPrice(resultSet.getInt("price"));
				registration.setNote(resultSet.getString("note"));
				registration.setActive(resultSet.getBoolean("is_active"));
				registration.setClinicAddress(resultSet.getString("clinic_address"));

				registrations.add(registration);
				System.out.println("Loaded Registration: " + registration.getFullName());
			}
		} catch (SQLException e) {
			System.out
					.println("Error while fetching registrations by Doctor ID and inactive status: " + e.getMessage());
			e.printStackTrace();
		}
		return registrations;
	}

	@Override
	public int countRegistrationByDoctorIdAndIsNotActive(int doctorId) {
		String sql = "SELECT COUNT(*) AS total FROM registration WHERE doctor_id = ? AND is_active = false AND day >= CURDATE()";
		System.out.println("Executing SQL: " + sql);
		int totalRegistrations = 0;

		try (Connection connection = connectionPool.getConnection("RegistrationService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Gán tham số cho câu lệnh SQL
			preparedStatement.setInt(1, doctorId);

			// Thực thi câu lệnh
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				totalRegistrations = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			System.out
					.println("Error while counting registrations by Doctor ID and inactive status: " + e.getMessage());
			e.printStackTrace();
		}

		return totalRegistrations;
	}

}
