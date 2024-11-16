package com.example.bookingcare.service.admin;

import java.sql.SQLException;
import java.util.List;

import com.example.bookingcare.model.Admins;

public interface AdminService {
	// Lấy danh sách tất cả các admin
	List<Admins> getAllAdmins();

	// Thêm admin mới
	void addAdmin(Admins admin) throws SQLException;

	// Cập nhật thông tin admin
	void updateAdmin(Admins admin) throws SQLException;

	// Xóa admin theo ID
	void deleteAdmin(int id) throws SQLException;

	// Tìm admin theo tên
	Admins findAdminByUsername(String username);

	Admins getAdminByID(int ID);
}
