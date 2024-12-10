package com.example.bookingcare.service.doctor;

import java.sql.SQLException;
import java.util.List;

import com.example.bookingcare.model.Specialty;

public interface SpecialtyService {

	// Lấy tất cả các chuyên khoa
	List<Specialty> getAllSpecialties();

	List<Specialty> getAllSpecialties(int page, int pageSize);

	// Thêm chuyên khoa mới
	void addSpecialty(Specialty specialty) throws SQLException;

	// Cập nhật thông tin chuyên khoa
	void updateSpecialty(Specialty specialty) throws SQLException;

	// Xóa chuyên khoa theo ID
	void deleteSpecialty(int id) throws SQLException;

	// Lấy thông tin chuyên khoa theo ID
	Specialty getSpecialtyById(int id);

	List<Specialty> findSpecialtyByName(String name);

}