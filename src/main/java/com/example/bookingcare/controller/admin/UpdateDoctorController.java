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

import com.example.bookingcare.model.Admins;
import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.admin.AdminServiceImpl;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller
public class UpdateDoctorController {

	@Autowired
	private DoctorServiceImpl doctorService;
	

	@Autowired
	private ClinicServiceImpl clinicService;
	
	  @Autowired
	    private SpecialtyServiceImpl specialtyService;
	

	  @GetMapping("/admin/sua-bac-si")
	  public String adminDetail(@RequestParam("id") int id, Model model) {
	      Doctor doctor = doctorService.getDoctorById(id);
	      List<Clinic> clinics = clinicService.getAllClinics(1, 100);
	      List<Specialty> specialties = specialtyService.getAllSpecialties(1, 100);
	      model.addAttribute("clinics", clinics);
	      model.addAttribute("specialties", specialties);
	      model.addAttribute("doctor", doctor);
	      return "admin/updateDoctor"; 
	  }

//"/submit_update"
	  @PostMapping("/admin/sua-bac-si/submit_update")
	  public String submitUpdateDoctor(
	          @RequestParam("id") int id,  
	          @RequestParam("username") String username,
	          @RequestParam("password") String password,
	          @RequestParam("fullname") String fullname,
	          @RequestParam("phonenumber") String phonenumber,
	          @RequestParam("address") String address,
	          @RequestParam("email") String email,
	          @RequestParam("position") String position,
	          @RequestParam("experience") double experience,
	          @RequestParam("description") String description,
	          @RequestParam("price") int price,
	          @RequestParam("specialtyId") int specialtyId,
	          @RequestParam(value = "avatarUrl", required = false) MultipartFile avatarUrl) throws SQLException {

	      // Lấy bác sĩ hiện tại theo id
	      Doctor doctor = doctorService.getDoctorById(id);

	      // Cập nhật thông tin bác sĩ
	      doctor.setUsername(username);
	      doctor.setPassword(password);
	      doctor.setFullname(fullname);
	      doctor.setPhonenumber(phonenumber);
	      doctor.setAddress(address);
	      doctor.setEmail(email);
	      doctor.setPosition(position);
	      doctor.setExperience(experience);
	      doctor.setDescription(description);
	      doctor.setPrice(price);
	      doctor.setSpecialtyId(specialtyId);

	      // Kiểm tra nếu avatar được upload
	      if (avatarUrl != null && !avatarUrl.isEmpty()) {
	          // Lưu avatar vào thư mục hoặc xử lý theo cách khác
	          String avatarFileName = specialtyService.saveLogo(avatarUrl); // Tạo phương thức để lưu avatar
	          doctor.setAvatarUrl(avatarFileName);  // Lưu tên file hoặc URL
	      }

	      // Lưu lại bác sĩ sau khi cập nhật
	      doctorService.updateDoctor(doctor);

	      // Chuyển hướng về trang danh sách bác sĩ sau khi cập nhật
	      return "redirect:/admin/quan-ly-bac-sy";
	  }

}
