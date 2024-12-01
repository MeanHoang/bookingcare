package com.example.bookingcare.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Admins;
import com.example.bookingcare.service.admin.AdminServiceImpl;

@Controller
public class AddAdminController {

	@Autowired
	private AdminServiceImpl adminService;

	
}
