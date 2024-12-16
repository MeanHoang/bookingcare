package com.example.bookingcare.service.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.bookingcare.connection.ConnectionPoolImpl;
import com.example.bookingcare.model.Admins;

@Service
public class AdminServiceImpl implements AdminService {

	private ConnectionPoolImpl connectionPool;

	public AdminServiceImpl() {
		this.connectionPool = new ConnectionPoolImpl(); // Khởi tạo connection pool
	}

	@Override
	public List<Admins> getAllAdmins(int page, int size) {
		List<Admins> adminsList = new ArrayList<>();
		String sql = "SELECT * FROM admins LIMIT ? OFFSET ?";

		try (Connection connection = connectionPool.getConnection("AdminService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Tính toán giá trị LIMIT và OFFSET
			int offset = (page - 1) * size;

			preparedStatement.setInt(1, size);
			preparedStatement.setInt(2, offset);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Admins admin = new Admins();
					admin.setID(resultSet.getInt("id"));
					admin.setUsername(resultSet.getString("username"));
					admin.setPassword(resultSet.getString("password"));
					admin.setName(resultSet.getString("name"));
					adminsList.add(admin);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminsList;
	}

	@Override
	public void addAdmin(Admins admin) throws SQLException {
		try (Connection connection = connectionPool.getConnection("AdminService")) {
			String query = "INSERT INTO admins (username, password, name,is_active) VALUES (?, ?, ?,?)";
			try (PreparedStatement stmt = connection.prepareStatement(query)) {

				stmt.setString(1, admin.getUsername());
				stmt.setString(2, admin.getPassword());
				stmt.setString(3, admin.getName());
				stmt.setBoolean(4, admin.isActive());
				stmt.executeUpdate();
			}
		}

	}

	public int getTotalPages(int size) {
		int totalRecords = 0;
		String countSql = "SELECT COUNT(*) FROM admins"; // Truy vấn để đếm tổng số bản ghi trong bảng clinic

		try (Connection connection = connectionPool.getConnection("AdminService");
				PreparedStatement countStmt = connection.prepareStatement(countSql);
				ResultSet countResult = countStmt.executeQuery()) {

			if (countResult.next()) {
				totalRecords = countResult.getInt(1); // Lấy tổng số bản ghi từ kết quả truy vấn
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý lỗi
		}

		// Tính tổng số trang bằng cách chia tổng số bản ghi cho kích thước mỗi trang
		// (size)
		return (int) Math.ceil((double) totalRecords / size); // Làm tròn lên nếu có phần dư
	}

	@Override
	public void updateAdmin(Admins admin) throws SQLException {
		// TODO Auto-generated method stub
		try (Connection connection = connectionPool.getConnection("AdminService")) {
			String query = "UPDATE admins SET username = ?, password = ?, name = ?,is_active = ? WHERE id = ?";
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, admin.getUsername());
				stmt.setString(2, admin.getPassword());
				stmt.setString(3, admin.getName());
				stmt.setBoolean(4, admin.isActive());
				stmt.setInt(5, admin.getID());
				stmt.executeUpdate();
			}
		}
	}

	@Override
	public void deleteAdmin(int id) throws SQLException {
		try (Connection connection = connectionPool.getConnection("AdminService")) {
			String query = "DELETE FROM admins WHERE id = ?";
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setInt(1, id);
				stmt.executeUpdate();
				System.out.println(" Deleted successful admin with id: " + id);
			} catch (Exception e) {
				System.out.println(" Deleted failed admin with id: " + id);
				System.out.println(" Deleted failed admin with e: " + e);

			}
		}

	}

	@Override
	public List<Admins> findAdminsByName(String name) {
		List<Admins> admins = new ArrayList<>();

		// Câu lệnh SQL để tìm admin theo tên
		String sql = "SELECT * FROM admins WHERE name LIKE ?";

		try (Connection connection = connectionPool.getConnection("AdminService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Thiết lập tham số vào PreparedStatement (tìm kiếm với LIKE)
			preparedStatement.setString(1, "%" + name + "%");

			// Thực thi truy vấn
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Lặp qua các kết quả tìm được
				while (resultSet.next()) {
					Admins admin = new Admins();
					admin.setID(resultSet.getInt("Id"));
					admin.setUsername(resultSet.getString("username"));
					admin.setPassword(resultSet.getString("password"));
					admin.setActive(resultSet.getBoolean("is_active"));
					admin.setName(resultSet.getString("name"));

					// Thêm admin vào danh sách
					admins.add(admin);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý lỗi truy vấn
		}

		return admins; // Trả về danh sách Admin hoặc danh sách rỗng nếu không tìm thấy
	}

	@Override
	public Admins getAdminByID(int ID) {
		Admins admin = null;
		String sql = "SELECT * FROM admins WHERE id=?";

		try (Connection connection = connectionPool.getConnection("AdminService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, ID);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					admin = new Admins();
					admin.setID(resultSet.getInt("id"));
					admin.setUsername(resultSet.getString("username"));
					admin.setPassword(resultSet.getString("password"));
					admin.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public Admins login(String username, String password) {
		Admins admin = null;

		// Câu lệnh SQL để tìm admin theo username và password
		String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";

		try (Connection connection = connectionPool.getConnection("AdminService");
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Thiết lập tham số vào PreparedStatement
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			// Thực thi truy vấn
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Nếu tìm thấy admin trong cơ sở dữ liệu
				if (resultSet.next()) {
					admin = new Admins();
					admin.setID(resultSet.getInt("id"));
					admin.setUsername(resultSet.getString("username"));
					admin.setPassword(resultSet.getString("password"));
					admin.setName(resultSet.getString("name"));

					// In thông tin admin ra console (nếu cần)
					System.out.println(admin);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Đảm bảo xử lý các lỗi có thể xảy ra khi truy vấn
		}

		return admin; // Trả về đối tượng Admin nếu đăng nhập thành công, ngược lại trả về null
	}


	@Override
	public List<Map<String, Object>> getRevenueByDate(String inputDate) {
	    List<Map<String, Object>> revenueList = new ArrayList<>();
	    String sql = "SELECT r.doctor_id, d.fullname AS doctor_name, SUM(r.price) AS total_revenue " +
	                 "FROM registration r " +
	                 "JOIN doctor d ON r.doctor_id = d.id " +  
	                 "WHERE r.day = ? AND r.is_active = TRUE " +
	                 "GROUP BY r.doctor_id, d.fullname";  
	    try (Connection connection = connectionPool.getConnection("AdminService");
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        preparedStatement.setString(1, inputDate); // Set ngày cần tính doanh thu

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                Map<String, Object> revenueMap = new HashMap<>();
	                revenueMap.put("doctorId", resultSet.getInt("doctor_id"));
	                revenueMap.put("doctorName", resultSet.getString("doctor_name"));
	                revenueMap.put("totalRevenue", resultSet.getInt("total_revenue"));
	                revenueList.add(revenueMap);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return revenueList;
	}



	@Override
	public List<Map<String, Object>> getRevenueByMonth(int year, int month) {
	    List<Map<String, Object>> revenueList = new ArrayList<>();
	    String sql = "SELECT r.doctor_id, d.fullname AS doctor_name, SUM(r.price) AS total_revenue " +
	                 "FROM registration r " +
	                 "JOIN doctor d ON r.doctor_id = d.id " +  
	                 "WHERE YEAR(r.day) = ? AND MONTH(r.day) = ? AND r.is_active = TRUE " +
	                 "GROUP BY r.doctor_id, d.fullname";  
	    try (Connection connection = connectionPool.getConnection("AdminService");
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        preparedStatement.setInt(1, year);  // Set năm
	        preparedStatement.setInt(2, month); // Set tháng

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                Map<String, Object> revenueMap = new HashMap<>();
	                revenueMap.put("doctorId", resultSet.getInt("doctor_id"));
	                revenueMap.put("doctorName", resultSet.getString("doctor_name"));
	                revenueMap.put("totalRevenue", resultSet.getInt("total_revenue"));
	                revenueList.add(revenueMap);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return revenueList;
	}

	@Override
	public List<Map<String, Object>> getRevenueByYear(int year) {
	    List<Map<String, Object>> revenueList = new ArrayList<>();
	    String sql = "SELECT r.doctor_id, d.fullname AS doctor_name, SUM(r.price) AS total_revenue " +
	                 "FROM registration r " +
	                 "JOIN doctor d ON r.doctor_id = d.id " +  
	                 "WHERE YEAR(r.day) = ? AND r.is_active = TRUE " +
	                 "GROUP BY r.doctor_id, d.fullname";  
	    try (Connection connection = connectionPool.getConnection("AdminService");
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        preparedStatement.setInt(1, year);  // Set năm

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                Map<String, Object> revenueMap = new HashMap<>();
	                revenueMap.put("doctorId", resultSet.getInt("doctor_id"));
	                revenueMap.put("doctorName", resultSet.getString("doctor_name"));
	                revenueMap.put("totalRevenue", resultSet.getInt("total_revenue"));
	                revenueList.add(revenueMap);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return revenueList;
	}
	@Override
	public int getTotalRevenueForCurrentMonth() {
	    int totalRevenue = 0;
	    String sql = "SELECT SUM(r.price) AS total_revenue " +
	                 "FROM registration r " +
	                 "WHERE YEAR(r.day) = ? AND MONTH(r.day) = ? AND r.is_active = TRUE";

	    // Lấy năm và tháng hiện tại
	    java.util.Calendar calendar = java.util.Calendar.getInstance();
	    int currentYear = calendar.get(java.util.Calendar.YEAR);
	    int currentMonth = calendar.get(java.util.Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cần +1

	    try (Connection connection = connectionPool.getConnection("AdminService");
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        // Gán giá trị cho tham số
	        preparedStatement.setInt(1, currentYear);
	        preparedStatement.setInt(2, currentMonth);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                totalRevenue = resultSet.getInt("total_revenue");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return totalRevenue;
	}

	public int getTotalRevenueForMonth(int month) {
	    int totalRevenue = 0;
	    String sql = "SELECT SUM(r.price) AS total_revenue " +
	                 "FROM registration r " +
	                 "WHERE YEAR(r.day) = ? AND MONTH(r.day) = ? AND r.is_active = TRUE";

	    // Lấy năm hiện tại
	    java.util.Calendar calendar = java.util.Calendar.getInstance();
	    int currentYear = calendar.get(java.util.Calendar.YEAR);

	    try (Connection connection = connectionPool.getConnection("AdminService");
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        // Gán giá trị cho tham số
	        preparedStatement.setInt(1, currentYear);
	        preparedStatement.setInt(2, month);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                totalRevenue = resultSet.getInt("total_revenue");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return totalRevenue;
	}


}

// Các phương thức khác như thêm, sửa, xóa admin có thể được triển khai ở đây