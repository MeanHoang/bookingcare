package com.example.bookingcare.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;

@Controller
public class ClinicDetailController {

	    @Autowired
	    private ClinicServiceImpl clinicService;

	    // Phương thức để xem chi tiết bác sĩ sử dụng @RequestParam
	    @GetMapping("/admin/thong-tin-csyt")
	    public String viewDoctorDetail(@RequestParam("id") int id, Model model) {
	        // Lấy thông tin bác sĩ từ service
	        Clinic clinic = clinicService.getClinicById(id);

	        // Kiểm tra xem bác sĩ có tồn tại không
	        if (clinic != null) {
	            // Thêm thông tin bác sĩ vào model để hiển thị trên trang
	        	System.out.print(clinic);
	            model.addAttribute("clinic", clinic);
	            return "admin/clinicDetail"; // Trả về view chứa chi tiết bác sĩ
	        } else {
	            // Nếu bác sĩ không tồn tại, trả về trang lỗi hoặc trang khác
	            return "redirect:/admin/quan-ly-csyt"; // Quay lại trang danh sách bác sĩ
	        }
	    }
	}

