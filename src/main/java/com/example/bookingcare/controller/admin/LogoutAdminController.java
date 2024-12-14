package com.example.bookingcare.controller.admin;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutAdminController {


	   @GetMapping("/admin/dang-xuat")
	    public String ConfirmLogout(HttpSession session) {
	        // Hủy session
	        session.invalidate();

	        // Chuyển hướng về trang đăng nhập
	        return "redirect:/admin/login";
	    }
	

}
