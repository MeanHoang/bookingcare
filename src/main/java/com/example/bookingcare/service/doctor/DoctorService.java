package com.example.bookingcare.service.doctor;

import java.sql.SQLException;
import java.util.List;

import com.example.bookingcare.model.Doctor;

public interface DoctorService {

	// Lấy danh sách tất cả các bác sĩ
	List<Doctor> getAllDoctors();

	List<Doctor> getAllDoctorsWithPage(int pageNumber, int pageSize);

	// Thêm bác sĩ mới
	void addDoctor(Doctor doctor) throws SQLException;

	// Cập nhật thông tin bác sĩ
	void updateDoctor(Doctor doctor) throws SQLException;

	// Xóa bác sĩ theo ID
	void deleteDoctor(int id) throws SQLException;

	// Tìm bác sĩ theo tên đăng nhập
	Doctor findDoctorByUsername(String username) throws SQLException;

	Doctor getDoctorById(int id) throws SQLException;

	List<Doctor> findDoctorBySpecialtyId(int specialtyId);

	List<Doctor> findDoctorByClinicId(int clinicId);

	Doctor login(String username, String password);

	List<Doctor> findDoctorInHaNoiAndCategoryId(int categoryId);

	List<Doctor> findDoctorInHCMAndCategoryId(int categoryId);

	List<Doctor> getAllDoctors(int page, int size);

	List<Doctor> findDoctorsByName(String name);
	

}
