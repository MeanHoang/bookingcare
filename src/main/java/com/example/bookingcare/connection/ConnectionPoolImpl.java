package com.example.bookingcare.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
	// Trình điều khiển làm việc
	private String driver;

	// Tài khoản kết nối
	private String username;
	private String userpass;

	// Đường dẫn thực thi
	private String url;
	private Stack<Connection> pool;

	public ConnectionPoolImpl() {
		// Xác định trình điều khiển
		this.driver = "com.mysql.cj.jdbc.Driver";

		// Nạp trình điều khiển
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Xác định tài khoản
		this.username = "root";
		this.userpass = "Nguyenkim102@";

		// Xác định đường dẫn thực thi
		this.url = "jdbc:mysql://localhost:3306/bookingcare_db?allowMultiQueries=true";

		// đố tuowgnj lưu trữ kết nối
		this.pool = new Stack<>();
	}

	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub

		if (this.pool.isEmpty()) {
			System.out.println(objectName + " da khoi tao mot ket noi moi");
			return DriverManager.getConnection(this.url, this.username, this.userpass);
		} else {
			System.out.println(objectName + " da lay ra mot ket noi moi");
			return this.pool.pop();
		}

	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub

		System.out.println(objectName + "đã trả về một kết nối mới");
		this.pool.push(con);

	}

	protected void finalize() throws Throwable {
		this.pool.clear();
		this.pool = null;
		System.out.println("CPool is Closed");
	}

}
