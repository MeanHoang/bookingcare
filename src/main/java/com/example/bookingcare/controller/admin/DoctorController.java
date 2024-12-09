package com.example.bookingcare.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller
public class DoctorController {
	 @Autowired
	 private DoctorServiceImpl doctorService;
	 @GetMapping("/admin/tim-kiem-bac-si")
	 public String listSpecialty(
	         @RequestParam("name") String name, 
	         Model model) {

	     List<Doctor> doctors = doctorService.findDoctorsByName(name);

	     model.addAttribute("doctors", doctors); // Thêm danh sách specialties
	     model.addAttribute("searchName", name);  // Tên đã tìm kiếm

	     return "admin/searchDoctor"; // Chuyển đến view danh sách specialties
	 }

	 }
