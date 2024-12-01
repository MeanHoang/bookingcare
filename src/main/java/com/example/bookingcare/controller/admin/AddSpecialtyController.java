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

import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller
public class AddSpecialtyController {

    @Autowired
    private SpecialtyServiceImpl specialtyService;

    @GetMapping("/admin/them-chuyen-khoa")
    public String addSpecialty(Model model) {
        return "admin/addSpecialty"; // Trang Thêm chuyên khoa
    }

    @PostMapping("/admin/them-chuyen-khoa-thanh-cong")
    public String addSpecialtySuccessful(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("logoSpecialty") MultipartFile logoSpecialty) throws SQLException {

        Specialty specialty = new Specialty();
        specialty.setName(name);
        specialty.setDescription(description);

        // Lưu logo specialty nếu có
        if (!logoSpecialty.isEmpty()) {
            String fileName = specialtyService.saveLogo(logoSpecialty); // Gọi phương thức saveLogo để lưu file và lấy tên file
            if (fileName != null) {
                specialty.setLogoSpecialty( fileName);  // Lưu đường dẫn vào cơ sở dữ liệu (relative path)
            }
        }

        // Lưu specialty vào CSDL
        specialtyService.addSpecialty(specialty);

        // Chuyển hướng đến trang quản lý chuyên khoa
        return "redirect:/admin/quan-ly-chuyen-khoa";
    }



}
