package com.example.bookingcare.service.admin;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.example.bookingcare.model.Admins;
import com.example.bookingcare.model.Doctor;

public interface AdminService {
	// Lấy danh sách tất cả các admin
	List<Admins> getAllAdmins(int page, int size);

	// Thêm admin mới
	void addAdmin(Admins admin) throws SQLException;

	// Cập nhật thông tin admin
	void updateAdmin(Admins admin) throws SQLException;

	// Xóa admin theo ID
	void deleteAdmin(int id) throws SQLException;

	// Tìm admin theo tên
	public List<Admins> findAdminsByName(String name);

	Admins getAdminByID(int ID);
	
	Admins login(String username, String password);

	List<Map<String, Object>> getRevenueByDate(String inputDate)	;
	List<Map<String, Object>> getRevenueByMonth(int year, int month);

	List<Map<String, Object>> getRevenueByYear(int year);

	int getTotalRevenueForCurrentMonth();

}
