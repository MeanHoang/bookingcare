package com.example.bookingcare.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.bookingcare.connection.ConnectionPoolImpl;
import com.example.bookingcare.model.Admins;
import com.example.bookingcare.service.admin.AdminService;
import com.example.bookingcare.service.admin.AdminServiceImpl;

public class maintest {
	@SuppressWarnings("unused")
	private ConnectionPoolImpl connectionPool;
	private static AdminService adminService;

	// Khởi tạo AdminService
	static {
		adminService = new AdminServiceImpl();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Admins ad1 = new Admins();
		ad1.setID(11);
		ad1.setUsername("kimnam123");
		ad1.setPassword("1");
		ad1.setName("Nguyen Kim Nam ngu");
//	        try {
//				adminService.addAdmin(ad1);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

//	        try {
//				adminService.updateAdmin(ad1);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	        

		try {
			adminService.deleteAdmin(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Admins> admins = new ArrayList<>();
		try {
			admins = adminService.getAllAdmins();
			System.out.print(admins);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
