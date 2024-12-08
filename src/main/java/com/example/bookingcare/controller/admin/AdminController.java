package com.example.bookingcare.controller.admin;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Admins;
import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.model.Doctor;
import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.admin.AdminServiceImpl;
import com.example.bookingcare.service.doctor.ClinicService;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyService;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminServiceImpl adminService;

	@Autowired
	private ClinicServiceImpl clinicService;	

	@Autowired
	private SpecialtyServiceImpl specialtyService;
	
	@Autowired
	private DoctorServiceImpl doctorService;
	
	@GetMapping("/admin/home")
	  public String home(HttpSession session, Model model) {
        // Kiểm tra xem admin đã đăng nhập chưa
        Admins admin = (Admins) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";  // Nếu chưa đăng nhập, chuyển hướng về trang login
        }
        
        // Nếu đã đăng nhập, cho phép vào trang home
        return "admin/dashBoard";
    }
	@GetMapping("/addAdmin")
	public String addAdmin() {

		return "admin/addAdmin";
	}
	
	@GetMapping("admin/quan-ly-csyt")
	public String listClinic(
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        Model model) {
	    // Gọi service để lấy danh sách cơ sở y tế phân trang với page và size
	    List<Clinic> clinicList = clinicService.getAllClinics(page, size);

	    // Tính toán tổng số trang (dựa trên tổng số bản ghi và size)
	    int totalPages = clinicService.getTotalPages(size);

	    // Thêm danh sách cơ sở y tế và thông tin phân trang vào model
	    model.addAttribute("clinics", clinicList);  // Thêm danh sách clinic
	    model.addAttribute("currentPage", page);    // Trang hiện tại
	    model.addAttribute("totalPages", totalPages); // Tổng số trang
	    model.addAttribute("pageSize", size);       // Kích thước mỗi trang

	    return "admin/manageClinic"; // Chuyển đến view danh sách cơ sở y tế
	}
	@GetMapping("admin/quan-ly-chuyen-khoa")
	public String listSpecialty(
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        Model model) {
	    // Gọi service để lấy danh sách cơ sở y tế phân trang với page và size
	    List<Specialty> specialtyList = specialtyService.getAllSpecialties(page, size);

	    // Tính toán tổng số trang (dựa trên tổng số bản ghi và size)
	    int totalPages = specialtyService.getTotalPages(size);

	    // Thêm danh sách cơ sở y tế và thông tin phân trang vào model
	    model.addAttribute("specialty", specialtyList);  // Thêm danh sách clinic
	    model.addAttribute("currentPage", page);    // Trang hiện tại
	    model.addAttribute("totalPages", totalPages); // Tổng số trang
	    model.addAttribute("pageSize", size);       // Kích thước mỗi trang

	    return "admin/manageSpecialty"; // Chuyển đến view danh sách cơ sở y tế
	}
	@GetMapping("admin/quan-ly-bac-sy")
	public String listDoctors(
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "5") int size,
	        Model model) {
	    // Gọi service để lấy danh sách bác sĩ phân trang với page và size
	    List<Doctor> doctorList = doctorService.getAllDoctors(page, size);

	    // Tính toán tổng số trang (dựa trên tổng số bản ghi và size)
	    int totalPages = doctorService.getTotalPages(size);

	    // Thêm danh sách bác sĩ và thông tin phân trang vào model
	    model.addAttribute("doctors", doctorList);  // Thêm danh sách bác sĩ
	    model.addAttribute("currentPage", page);    // Trang hiện tại
	    model.addAttribute("totalPages", totalPages); // Tổng số trang
	    model.addAttribute("pageSize", size);       // Kích thước mỗi trang

	    return "admin/manageDoctor"; // Chuyển đến view danh sách bác sĩ
	}

	

	@PostMapping("/submit_registration")
	public String submitRegistration(@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("password") String password) throws SQLException {
		Admins ad1 = new Admins();
		ad1.setName(name);
		ad1.setUsername(username);
		ad1.setPassword(password);
		adminService.addAdmin(ad1);

		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
