package com.example.bookingcare.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller
public class DeleteDoctorController {

	@Autowired
    private DoctorServiceImpl doctorService;

	@PostMapping("admin/xoa-bac-si")
	public String deleteDoctor(@RequestParam("id") int doctorId) throws SQLException {
	    System.out.println("Deleting doctor with ID: " + doctorId);
	    
	    // Gọi service để xóa bác sĩ từ cơ sở dữ liệu
	    doctorService.deleteDoctor(doctorId);
	    
	    // Chuyển hướng về trang quản lý bác sĩ sau khi xóa
	    return "redirect:/admin/quan-ly-bac-sy";
	}


}
