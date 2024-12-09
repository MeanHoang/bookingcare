package com.example.bookingcare.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller
public class AddClinicController {


	@Autowired
	private ClinicServiceImpl clinicService;
	
    @GetMapping("/admin/them-csyt")
    public String addSpecialty(Model model) {
        return "admin/addClinic"; // Trang Thêm chuyên khoa
    }

    @PostMapping("/admin/them-csyt-thanh-cong")
    public String addClinicSuccessful(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("address") String address,
            @RequestParam("workingTime") String workingTime) throws SQLException {

        // Tạo đối tượng Clinic
        Clinic clinic = new Clinic();
        clinic.setName(name);
        clinic.setDescription(description);
        clinic.setAddress(address);
        clinic.setWorkingTime(workingTime);

        // Lưu Clinic vào CSDL qua service
        clinicService.addClinic(clinic);

        // Chuyển hướng đến trang quản lý cơ sở y tế
        return "redirect:/admin/quan-ly-csyt";
    }




}
