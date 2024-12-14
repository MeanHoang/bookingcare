package com.example.bookingcare.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bookingcare.model.Admins;

import jakarta.servlet.http.HttpSession;
@Controller
public class AdminProfileController {
	
	@GetMapping("/admin/thong-tin-ca-nhan")
	public String info(HttpSession session, Model model) {
        // Kiểm tra xem admin đã đăng nhập chưa
        Admins admin = (Admins) session.getAttribute("admin");

        if (admin == null) {
            return "redirect:/admin/login";  // Nếu chưa đăng nhập, chuyển đến trang login
        }

        model.addAttribute("admin", admin);  // Thêm thông tin admin vào model
        return "admin/adminProfile";  // Trả về trang thông tin cá nhân admin
    }
	
}
