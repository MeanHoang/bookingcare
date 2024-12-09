package com.example.bookingcare.service.doctor;

import java.sql.SQLException;
import java.util.List;

import com.example.bookingcare.model.Clinic;

public interface ClinicService {

	// Lấy tất cả các phòng khám
	List<Clinic> getAllClinics();

	List<Clinic> getAllClinics(int page, int size);

	// Thêm mới phòng khám
	void addClinic(Clinic clinic) throws SQLException;

	// Cập nhật thông tin phòng khám
	void updateClinic(Clinic clinic) throws SQLException;

	// Xóa phòng khám theo ID
	void deleteClinic(int id) throws SQLException;

	// Lấy thông tin phòng khám theo ID
	Clinic getClinicById(int id);

	List<Clinic> getClinicsByName(String name);
	

}
