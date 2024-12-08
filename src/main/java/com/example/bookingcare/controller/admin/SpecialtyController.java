package com.example.bookingcare.controller.admin;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller("adminSpecialtyController")
public class SpecialtyController {
	 @Autowired
	 private SpecialtyServiceImpl specialtyService;
	 @GetMapping("/admin/tim-kiem-chuyen-khoa")
	 public String listSpecialty(
	         @RequestParam("name") String name, 
	         Model model) {

	     // Gọi service để tìm kiếm specialties theo tên (không phân trang)
	     List<Specialty> specialtyList = specialtyService.findSpecialtyByName(name);

	     // Thêm danh sách specialties vào model
	     model.addAttribute("specialty", specialtyList); // Thêm danh sách specialties
	     model.addAttribute("searchName", name);  // Tên đã tìm kiếm

	     return "admin/searchSpecialty"; // Chuyển đến view danh sách specialties
	 }

	 }





