package com.example.bookingcare.controller.admin;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Gender;
import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller 
public class addDoctorController {

	@Autowired
	private DoctorServiceImpl doctorService;
	

	@Autowired
	private ClinicServiceImpl clinicService;
	
	  @Autowired
	    private SpecialtyServiceImpl specialtyService;
	
    @GetMapping("/admin/them-bac-sy")
    public String addSpecialty(Model model) {
    	 List<Clinic> clinics = clinicService.getAllClinics(1, 100);  // Lấy danh sách cơ sở y tế
    	 List<Specialty> specialties = specialtyService.getAllSpecialties(1, 100);
    	 model.addAttribute("clinics", clinics); 
    	 model.addAttribute("specialties", specialties); 

    	 
        return "admin/addDoctor"; // Trang Thêm chuyên khoa
    }

    @PostMapping("/admin/them-bac-si-thanh-cong")
    public String addDoctorSuccessful(
            @RequestParam("fullname") String fullname,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("phonenumber") String phonenumber,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            @RequestParam("birthday") String birthday, // Định dạng ngày có thể là String hoặc Date tùy theo yêu cầu
            @RequestParam("gender") String gender, // Hoặc có thể là enum tùy vào cách xử lý của bạn
            @RequestParam("experience") double experience,
            @RequestParam("description") String description,
            @RequestParam("price") int price,
            @RequestParam("clinicId") int clinicId,
            @RequestParam("specialtyId") int specialtyId,
            @RequestParam("avatarUrl") MultipartFile avatar) throws SQLException {
        
        // Tạo đối tượng Doctor
        Doctor doctor = new Doctor();
        doctor.setFullname(fullname);
        doctor.setUsername(username);
        doctor.setPassword(password);
        doctor.setPhonenumber(phonenumber);
        doctor.setAddress(address);
        doctor.setEmail(email);
        doctor.setBirthday(Date.valueOf(birthday)); // Chuyển đổi định dạng ngày nếu cần
        doctor.setGender(Gender.valueOf(gender.toUpperCase())); // Nếu Gender là enum
        doctor.setExperience(experience);
        doctor.setDescription(description);
        doctor.setPrice(price);
        doctor.setClinicId(clinicId);
        doctor.setSpecialtyId(specialtyId);

        // Lưu ảnh đại diện nếu có
        if (!avatar.isEmpty()) {
            String fileName = doctorService.saveLogo(avatar); // Giả sử bạn có một phương thức để lưu ảnh và trả về tên file
            if (fileName != null) {
                doctor.setAvatarUrl(fileName);  // Lưu đường dẫn vào cơ sở dữ liệu
            }
        }

        // Lưu bác sĩ vào CSDL
        doctorService.addDoctor(doctor);

        // Chuyển hướng đến trang quản lý bác sĩ
        return "redirect:/admin/quan-ly-bac-sy";
    }

}


