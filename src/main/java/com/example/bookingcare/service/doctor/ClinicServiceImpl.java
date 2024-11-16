package com.example.bookingcare.service.doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookingcare.connection.ConnectionPoolImpl;
import com.example.bookingcare.model.Clinic;

@Service
public class ClinicServiceImpl implements ClinicService {

	private final ConnectionPoolImpl connectionPool;

	public ClinicServiceImpl() {
		this.connectionPool = new ConnectionPoolImpl();
	}

	@Override
	public List<Clinic> getAllClinics() {
		List<Clinic> clinicList = new ArrayList<>();
		String sql = "SELECT * FROM clinic";
		try (Connection connection = connectionPool.getConnection("ClinicService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Clinic clinic = new Clinic();
				clinic.setId(resultSet.getInt("id"));
				clinic.setName(resultSet.getString("name"));
				clinic.setDescription(resultSet.getString("des"));
				clinic.setAddress(resultSet.getString("address"));
				clinic.setWorkingTime(resultSet.getString("working_time"));
				clinicList.add(clinic);

				// In ra từng bản ghi để kiểm tra
				System.out.println("ID: " + clinic.getId());
				System.out.println("Name: " + clinic.getName());
				System.out.println("Description: " + clinic.getDescription());
				System.out.println("Address: " + clinic.getAddress());
				System.out.println("Working Time: " + clinic.getWorkingTime());
			}

		} catch (SQLException e) {
			System.err.println("Lỗi SQL: " + e.getMessage());
			e.printStackTrace();
		}
		return clinicList;
	}

	@Override
	public void addClinic(Clinic clinic) throws SQLException {
		String query = "INSERT INTO clinic (name, des, address, working_time) VALUES (?, ?, ?, ?)";

		try (Connection connection = connectionPool.getConnection("ClinicService");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setString(1, clinic.getName());
			stmt.setString(2, clinic.getDescription());
			stmt.setString(3, clinic.getAddress());
			stmt.setString(4, clinic.getWorkingTime());

			stmt.executeUpdate();
		}
	}

	@Override
	public void updateClinic(Clinic clinic) throws SQLException {
		String query = "UPDATE clinic SET name=?, des=?, address=?, working_time=? WHERE id=?";

		try (Connection connection = connectionPool.getConnection("ClinicService");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setString(1, clinic.getName());
			stmt.setString(2, clinic.getDescription());
			stmt.setString(3, clinic.getAddress());
			stmt.setString(4, clinic.getWorkingTime());
			stmt.setInt(5, clinic.getId());

			stmt.executeUpdate();
		}
	}

	@Override
	public void deleteClinic(int id) throws SQLException {
		String query = "DELETE FROM clinic WHERE id=?";

		try (Connection connection = connectionPool.getConnection("ClinicService");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

	@Override
	public Clinic getClinicById(int id) {
		Clinic clinic = null;
		String sql = "SELECT * FROM clinic WHERE id=?";

		try (Connection connection = connectionPool.getConnection("ClinicService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					clinic = new Clinic();
					clinic.setId(resultSet.getInt("id"));
					clinic.setName(resultSet.getString("name"));
					clinic.setDescription(resultSet.getString("des"));
					clinic.setAddress(resultSet.getString("address"));
					clinic.setWorkingTime(resultSet.getString("working_time"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clinic;
	}
}