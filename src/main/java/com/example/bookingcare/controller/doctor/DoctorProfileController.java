package com.example.bookingcare.controller.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.doctor.DoctorService;

import jakarta.servlet.http.HttpSession;
@Controller
public class DoctorProfileController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctor/thong-tin-ca-nhan")
    public String viewUserProfile(HttpSession session, Model model) {
        // Lấy đối tượng doctor từ session
        Doctor doctor = (Doctor) session.getAttribute("doctor");

        if (doctor == null) {
            // Nếu không có thông tin bác sĩ trong session, chuyển hướng đến trang login
            return "redirect:/doctor/login"; // Chuyển hướng đến trang đăng nhập
        }

        // Thêm doctor vào model để hiển thị trong view
        model.addAttribute("doctor", doctor);
        
        // Chuyển đến trang thông tin bác sĩ
        return "doctor/doctorProfile"; // Đảm bảo rằng trang doctorProfile tồn tại
    }
}
