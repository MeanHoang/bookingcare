package com.example.bookingcare.service.doctor;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.bookingcare.connection.ConnectionPoolImpl;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Gender;

@Service
public class DoctorServiceImpl implements DoctorService {

	private final ConnectionPoolImpl connectionPool;

	public DoctorServiceImpl() {
		this.connectionPool = new ConnectionPoolImpl();
	}

	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctorList = new ArrayList<>();
		String sql = "SELECT * FROM doctor";

		try (Connection connection = connectionPool.getConnection("DoctorService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Doctor doctor = new Doctor();
				doctor.setId(resultSet.getInt("id"));
				doctor.setFullname(resultSet.getString("fullname"));
				doctor.setUsername(resultSet.getString("username"));
				doctor.setPassword(resultSet.getString("password"));
				doctor.setPhonenumber(resultSet.getString("phonenumber"));
				doctor.setAddress(resultSet.getString("address"));
				doctor.setAvatarUrl(resultSet.getString("avatar_url"));
				doctor.setPosition(resultSet.getString("position"));
				doctor.setEmail(resultSet.getString("email"));
				doctor.setBirthday(resultSet.getDate("birthday"));

				// Chuyển đổi chuỗi sang enum Gender
				String genderStr = resultSet.getString("gender");
				doctor.setGender(Gender.valueOf(genderStr.toUpperCase()));

				doctor.setExperience(resultSet.getDouble("exp"));
				doctor.setDescription(resultSet.getString("des"));
				doctor.setPrice(resultSet.getInt("price"));
				doctor.setClinicId(resultSet.getInt("clinic_id"));
				doctor.setSpecialtyId(resultSet.getInt("specialty_id"));
				doctorList.add(doctor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorList;
	}

	@Override
	public List<Doctor> getAllDoctors(int page, int size) {
		List<Doctor> doctorList = new ArrayList<>();
		// Sửa câu truy vấn SQL để thêm phân trang
		String sql = "SELECT * FROM doctor LIMIT ? OFFSET ?";

		// Tính toán offset (vị trí bắt đầu) cho phân trang
		int offset = (page - 1) * size;

		try (Connection connection = connectionPool.getConnection("DoctorService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, size); // Số lượng bản ghi mỗi trang
			preparedStatement.setInt(2, offset); // Vị trí bắt đầu (offset)

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Doctor doctor = new Doctor();
					doctor.setId(resultSet.getInt("id"));
					doctor.setFullname(resultSet.getString("fullname"));
					doctor.setUsername(resultSet.getString("username"));
					doctor.setPassword(resultSet.getString("password"));
					doctor.setPhonenumber(resultSet.getString("phonenumber"));
					doctor.setAddress(resultSet.getString("address"));
					doctor.setAvatarUrl(resultSet.getString("avatar_url"));
					doctor.setPosition(resultSet.getString("position"));
					doctor.setEmail(resultSet.getString("email"));
					doctor.setBirthday(resultSet.getDate("birthday"));

					// Chuyển đổi chuỗi sang enum Gender
					String genderStr = resultSet.getString("gender");
					doctor.setGender(Gender.valueOf(genderStr.toUpperCase()));

					doctor.setExperience(resultSet.getDouble("exp"));
					doctor.setDescription(resultSet.getString("des"));
					doctor.setPrice(resultSet.getInt("price"));
					doctor.setClinicId(resultSet.getInt("clinic_id"));
					doctor.setSpecialtyId(resultSet.getInt("specialty_id"));
					doctorList.add(doctor);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorList;
	}

	public int getTotalPages(int size) {
		String sql = "SELECT COUNT(*) FROM doctor";
		int totalRecords = 0;
		try (Connection connection = connectionPool.getConnection("DoctorService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				totalRecords = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Tính tổng số trang, làm tròn lên nếu có dư bản ghi
		return (int) Math.ceil((double) totalRecords / size);
	}

	@Override
	public void addDoctor(Doctor doctor) throws SQLException {
		String query = "INSERT INTO doctor ( fullname, username, password, phonenumber, address, avatar_url, position, email, birthday, gender, exp, des, price, clinic_id, specialty_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = connectionPool.getConnection("DoctorService");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setString(1, doctor.getFullname());
			stmt.setString(2, doctor.getUsername());
			stmt.setString(3, doctor.getPassword());
			stmt.setString(4, doctor.getPhonenumber());
			stmt.setString(5, doctor.getAddress());
			stmt.setString(6, doctor.getAvatarUrl());
			stmt.setString(7, doctor.getPosition());
			stmt.setString(8, doctor.getEmail());
			stmt.setDate(9, new Date(doctor.getBirthday().getTime()));

			// Chuyển đổi enum Gender thành chuỗi
			stmt.setString(10, doctor.getGender().name());

			stmt.setDouble(11, doctor.getExperience());
			stmt.setString(12, doctor.getDescription());
			stmt.setInt(13, doctor.getPrice());
			stmt.setInt(14, doctor.getClinicId());
			stmt.setInt(15, doctor.getSpecialtyId());

			stmt.executeUpdate();
		}
	}

	public String saveLogo(MultipartFile file) {
		try {
			String uploadDir = "D:/bookingcare/src/main/resources/static/images";
			String fileName = file.getOriginalFilename();
			File dest = new File(uploadDir, fileName);
			file.transferTo(dest);
			return fileName; // Trả về tên tệp để lưu vào cơ sở dữ liệu
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateDoctor(Doctor doctor) throws SQLException {
		String query = "UPDATE doctor SET fullname=?, username=?, password=?, phonenumber=?, address=?, avatar_url=?, position=?, email=?, birthday=?, gender=?, exp=?, des=?, price=?, clinic_id=?, specialty_id=? WHERE id=?";

		try (Connection connection = connectionPool.getConnection("DoctorService");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setString(1, doctor.getFullname());
			stmt.setString(2, doctor.getUsername());
			stmt.setString(3, doctor.getPassword());
			stmt.setString(4, doctor.getPhonenumber());
			stmt.setString(5, doctor.getAddress());
			stmt.setString(6, doctor.getAvatarUrl());
			stmt.setString(7, doctor.getPosition());
			stmt.setString(8, doctor.getEmail());
			stmt.setDate(9, new Date(doctor.getBirthday().getTime()));
			stmt.setString(10, doctor.getGender().name());
			stmt.setDouble(11, doctor.getExperience());
			stmt.setString(12, doctor.getDescription());
			stmt.setInt(13, doctor.getPrice());
			stmt.setInt(14, doctor.getClinicId());
			stmt.setInt(15, doctor.getSpecialtyId());
			stmt.setInt(16, doctor.getId());

			stmt.executeUpdate();
		}
	}

	@Override
	public void deleteDoctor(int id) throws SQLException {
		String query = "DELETE FROM doctor WHERE id=?";

		try (Connection connection = connectionPool.getConnection("DoctorService");
				PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

	@Override
	public Doctor getDoctorById(int id) {
		Doctor doctor = null;
		String sql = "SELECT * FROM doctor WHERE id=?";

		try (Connection connection = connectionPool.getConnection("DoctorService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					doctor = new Doctor();
					doctor.setId(resultSet.getInt("id"));
					doctor.setFullname(resultSet.getString("fullname"));
					doctor.setUsername(resultSet.getString("username"));
					doctor.setPassword(resultSet.getString("password"));
					doctor.setPhonenumber(resultSet.getString("phonenumber"));
					doctor.setAddress(resultSet.getString("address"));
					doctor.setAvatarUrl(resultSet.getString("avatar_url"));
					doctor.setPosition(resultSet.getString("position"));
					doctor.setEmail(resultSet.getString("email"));
					doctor.setBirthday(resultSet.getDate("birthday"));

					// Chuyển đổi chuỗi sang enum Gender
					String genderStr = resultSet.getString("gender");
					doctor.setGender(Gender.valueOf(genderStr.toUpperCase()));

					doctor.setExperience(resultSet.getDouble("exp"));
					doctor.setDescription(resultSet.getString("des"));
					doctor.setPrice(resultSet.getInt("price"));
					doctor.setClinicId(resultSet.getInt("clinic_id"));
					doctor.setSpecialtyId(resultSet.getInt("specialty_id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctor;
	}

	@Override
	public Doctor findDoctorByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> findDoctorBySpecialtyId(int specialtyId) {
		List<Doctor> doctorList = new ArrayList<>();
		String sql = "SELECT * FROM doctor WHERE specialty_id = ?";

		try (Connection connection = connectionPool.getConnection("DoctorService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Thiết lập giá trị cho tham số đầu tiên
			preparedStatement.setInt(1, specialtyId);

			// Thực thi truy vấn sau khi đã set tham số
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Doctor doctor = new Doctor();
					doctor.setId(resultSet.getInt("id"));
					doctor.setFullname(resultSet.getString("fullname"));
					doctor.setUsername(resultSet.getString("username"));
					doctor.setPassword(resultSet.getString("password"));
					doctor.setPhonenumber(resultSet.getString("phonenumber"));
					doctor.setAddress(resultSet.getString("address"));
					doctor.setAvatarUrl(resultSet.getString("avatar_url"));
					doctor.setPosition(resultSet.getString("position"));
					doctor.setEmail(resultSet.getString("email"));
					doctor.setBirthday(resultSet.getDate("birthday"));

					// Chuyển đổi chuỗi sang enum Gender
					String genderStr = resultSet.getString("gender");
					doctor.setGender(Gender.valueOf(genderStr.toUpperCase()));

					doctor.setExperience(resultSet.getDouble("exp"));
					doctor.setDescription(resultSet.getString("des"));
					doctor.setPrice(resultSet.getInt("price"));
					doctor.setClinicId(resultSet.getInt("clinic_id"));
					doctor.setSpecialtyId(resultSet.getInt("specialty_id"));

					doctorList.add(doctor);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorList;
	}

	@Override
	public List<Doctor> findDoctorByClinicId(int clinicId) {
		// TODO Auto-generated method stub
		List<Doctor> doctorList = new ArrayList<>();
		String sql = "SELECT * FROM doctor WHERE clinic_id = ?";

		try (Connection connection = connectionPool.getConnection("DoctorService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Thiết lập giá trị cho tham số đầu tiên
			preparedStatement.setInt(1, clinicId);

			// Thực thi truy vấn sau khi đã set tham số
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Doctor doctor = new Doctor();
					doctor.setId(resultSet.getInt("id"));
					doctor.setFullname(resultSet.getString("fullname"));
					doctor.setUsername(resultSet.getString("username"));
					doctor.setPassword(resultSet.getString("password"));
					doctor.setPhonenumber(resultSet.getString("phonenumber"));
					doctor.setAddress(resultSet.getString("address"));
					doctor.setAvatarUrl(resultSet.getString("avatar_url"));
					doctor.setPosition(resultSet.getString("position"));
					doctor.setEmail(resultSet.getString("email"));
					doctor.setBirthday(resultSet.getDate("birthday"));

					// Chuyển đổi chuỗi sang enum Gender
					String genderStr = resultSet.getString("gender");
					doctor.setGender(Gender.valueOf(genderStr.toUpperCase()));

					doctor.setExperience(resultSet.getDouble("exp"));
					doctor.setDescription(resultSet.getString("des"));
					doctor.setPrice(resultSet.getInt("price"));
					doctor.setClinicId(resultSet.getInt("clinic_id"));
					doctor.setSpecialtyId(resultSet.getInt("specialty_id"));

					doctorList.add(doctor);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorList;
	}

	public Doctor login(String username, String password) {
		Doctor doctor = null;

		// Câu lệnh SQL để tìm bác sĩ theo username và password
		String sql = "SELECT * FROM doctor WHERE username = ? AND password = ?";

		try (Connection connection = connectionPool.getConnection("DoctorService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Thiết lập tham số vào PreparedStatement
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			// Thực thi truy vấn
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Nếu tìm thấy bác sĩ trong cơ sở dữ liệu
				if (resultSet.next()) {
					doctor = new Doctor();
					doctor.setId(resultSet.getInt("id"));
					doctor.setFullname(resultSet.getString("fullname"));
					doctor.setUsername(resultSet.getString("username"));
					doctor.setPassword(resultSet.getString("password"));
					doctor.setPhonenumber(resultSet.getString("phonenumber"));
					doctor.setAddress(resultSet.getString("address"));
					doctor.setAvatarUrl(resultSet.getString("avatar_url"));
					doctor.setPosition(resultSet.getString("position"));
					doctor.setEmail(resultSet.getString("email"));
					doctor.setBirthday(resultSet.getDate("birthday"));

					// Chuyển chuỗi gender thành enum
					String genderStr = resultSet.getString("gender");
					if (genderStr != null) {
						doctor.setGender(Gender.valueOf(genderStr.toUpperCase()));
					}

					doctor.setExperience(resultSet.getDouble("exp"));
					doctor.setDescription(resultSet.getString("des"));
					doctor.setPrice(resultSet.getInt("price"));
					doctor.setClinicId(resultSet.getInt("clinic_id"));
					doctor.setSpecialtyId(resultSet.getInt("specialty_id"));
					System.out.println(doctor);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Đảm bảo xử lý các lỗi có thể xảy ra khi truy vấn
		}

		return doctor; // Trả về đối tượng Doctor nếu đăng nhập thành công, ngược lại trả về null
	}

	@Override
	public List<Doctor> getAllDoctorsWithPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> findDoctorInHaNoiAndCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> findDoctorInHCMAndCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
}
