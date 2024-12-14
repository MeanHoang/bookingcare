package com.example.bookingcare.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;

@Controller("doctorDetailControllerAdmin")
public class DoctorDetailController {

	    @Autowired
	    private DoctorServiceImpl doctorService;

	    // Phương thức để xem chi tiết bác sĩ sử dụng @RequestParam
	    @GetMapping("/admin/thong-tin-bac-si")
	    public String viewDoctorDetail(@RequestParam("id") int id, Model model) {
	        // Lấy thông tin bác sĩ từ service
	        Doctor doctor = doctorService.getDoctorById(id);

	        // Kiểm tra xem bác sĩ có tồn tại không
	        if (doctor != null) {
	            // Thêm thông tin bác sĩ vào model để hiển thị trên trang
	        	System.out.print(doctor);
	            model.addAttribute("doctor", doctor);
	            return "admin/doctorDetail"; // Trả về view chứa chi tiết bác sĩ
	        } else {
	            // Nếu bác sĩ không tồn tại, trả về trang lỗi hoặc trang khác
	            return "redirect:/admin/quan-ly-bac-sy"; // Quay lại trang danh sách bác sĩ
	        }
	    }
	}

