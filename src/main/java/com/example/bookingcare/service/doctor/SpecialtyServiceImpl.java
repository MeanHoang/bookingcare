package com.example.bookingcare.service.doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookingcare.connection.ConnectionPoolImpl;
import com.example.bookingcare.model.Specialty;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

	private ConnectionPoolImpl connectionPool;

	public SpecialtyServiceImpl() {
		this.connectionPool = new ConnectionPoolImpl(); // Khởi tạo connection pool
	}

	@Override
	public List<Specialty> getAllSpecialties() {
		List<Specialty> specialties = new ArrayList<>();
		String sql = "SELECT * FROM specialty";
		System.out.println("Executing SQL: " + sql); // In SQL query ra System

		try (Connection connection = connectionPool.getConnection("SpecialtyService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Specialty specialty = new Specialty();
				specialty.setId(resultSet.getInt("id"));
				specialty.setName(resultSet.getString("name"));
				specialty.setDescription(resultSet.getString("des"));
				specialty.setLogoSpecialty(resultSet.getString("logo_specialty")); // Lấy logo specialty
				specialties.add(specialty);
				System.out.println("Loaded Specialty: " + specialty.getName()); // In ra tên chuyên khoa đã load
			}
		} catch (Exception e) {
			System.out.println("Error while fetching specialties: " + e.getMessage()); // In lỗi ra System
			e.printStackTrace();
		}
		return specialties;
	}

	@Override
	public void addSpecialty(Specialty specialty) throws SQLException {
		String query = "INSERT INTO specialty (name, des, logo_specialty) VALUES (?, ?, ?)";
		System.out.println("Executing SQL: " + query); // In SQL query ra System
		System.out.println("Adding Specialty: " + specialty.getName()); // In ra tên chuyên khoa đang thêm

		try (Connection connection = connectionPool.getConnection("SpecialtyService");
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, specialty.getName());
			stmt.setString(2, specialty.getDescription());
			stmt.setString(3, specialty.getLogoSpecialty()); // Chèn logo specialty
			stmt.executeUpdate();
			System.out.println("Specialty added successfully."); // In ra thông báo khi thêm thành công
		} catch (SQLException e) {
			System.out.println("Error while adding specialty: " + e.getMessage()); // In lỗi ra System
			e.printStackTrace();
		}
	}

	@Override
	public void updateSpecialty(Specialty specialty) throws SQLException {
		String query = "UPDATE specialty SET name = ?, des = ?, logo_specialty = ? WHERE id = ?";
		System.out.println("Executing SQL: " + query); // In SQL query ra System
		System.out.println("Updating Specialty with ID: " + specialty.getId()); // In ra ID chuyên khoa cần cập nhật

		try (Connection connection = connectionPool.getConnection("SpecialtyService");
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, specialty.getName());
			stmt.setString(2, specialty.getDescription());
			stmt.setString(3, specialty.getLogoSpecialty()); // Cập nhật logo specialty
			stmt.setInt(4, specialty.getId());
			stmt.executeUpdate();
			System.out.println("Specialty updated successfully."); // In ra thông báo khi cập nhật thành công
		} catch (SQLException e) {
			System.out.println("Error while updating specialty: " + e.getMessage()); // In lỗi ra System
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSpecialty(int id) throws SQLException {
		String query = "DELETE FROM specialty WHERE id = ?";
		System.out.println("Executing SQL: " + query); // In SQL query ra System
		System.out.println("Deleting Specialty with ID: " + id); // In ra ID chuyên khoa cần xóa

		try (Connection connection = connectionPool.getConnection("SpecialtyService");
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("Specialty deleted successfully."); // In ra thông báo khi xóa thành công
		} catch (SQLException e) {
			System.out.println("Error while deleting specialty: " + e.getMessage()); // In lỗi ra System
			e.printStackTrace();
		}
	}

	@Override
	public Specialty getSpecialtyById(int id) {
		String query = "SELECT * FROM specialty WHERE id = ?";
		Specialty specialty = null;
		System.out.println("Executing SQL: " + query); // In SQL query ra System
		System.out.println("Fetching Specialty with ID: " + id); // In ra ID chuyên khoa cần lấy

		try (Connection connection = connectionPool.getConnection("SpecialtyService");
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				specialty = new Specialty();
				specialty.setId(resultSet.getInt("id"));
				specialty.setName(resultSet.getString("name"));
				specialty.setDescription(resultSet.getString("des"));
				specialty.setLogoSpecialty(resultSet.getString("logo_specialty")); // Lấy logo specialty
				System.out.println("Specialty found: " + specialty.getName()); // In ra tên chuyên khoa đã tìm thấy
			} else {
				System.out.println("No Specialty found with ID: " + id); // In ra khi không tìm thấy chuyên khoa
			}
		} catch (SQLException e) {
			System.out.println("Error while fetching specialty: " + e.getMessage()); // In lỗi ra System
			e.printStackTrace();
		}
		return specialty;
	}
}