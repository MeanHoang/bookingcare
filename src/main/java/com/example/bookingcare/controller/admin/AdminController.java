package com.example.bookingcare.controller.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Admins;
import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.service.admin.AdminServiceImpl;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;
import com.example.bookingcare.service.doctor.DoctorServiceImpl;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;
import com.example.bookingcare.service.user.RegistrationServiceImpl;

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

	@Autowired
	private RegistrationServiceImpl registrationService;

	@GetMapping("/admin/home")
	public String home(HttpSession session, Model model) {
	    // Kiểm tra xem admin đã đăng nhập chưa
	    Admins admin = (Admins) session.getAttribute("admin");

	    if (admin == null) {
	        return "redirect:/admin/login";
	    }

	    // Lấy doanh thu cho 4 tháng liên tiếp
	    int doanhThuThang9 = adminService.getTotalRevenueForMonth(9);  // Doanh thu tháng 9
	    int doanhThuThang10 = adminService.getTotalRevenueForMonth(10); // Doanh thu tháng 10
	    int doanhThuThang11 = adminService.getTotalRevenueForMonth(11); // Doanh thu tháng 11
	    int doanhThuThang12 = adminService.getTotalRevenueForMonth(12); 

	    // Các thông tin khác
	    int doanhThu = adminService.getTotalRevenueForCurrentMonth();
	    int totalDoctors = doctorService.getTotalDoctors();
	    int totalClinics = clinicService.getTotalClinics();
	    int totalSpecialty = specialtyService.getTotalSpecialties();

	    // Truyền vào model
	    model.addAttribute("doanhThuThang9", doanhThuThang9);
	    model.addAttribute("doanhThuThang10", doanhThuThang10);
	    model.addAttribute("doanhThuThang11", doanhThuThang11);
	    model.addAttribute("doanhThuThang12", doanhThuThang12);
	    model.addAttribute("doanhThu", doanhThu);
	    model.addAttribute("totalDoctors", totalDoctors);
	    model.addAttribute("totalClinics", totalClinics);
	    model.addAttribute("totalSpecialty", totalSpecialty);

	    // Nếu đã đăng nhập, cho phép vào trang home
	    return "admin/dashBoard";
	}


	@GetMapping("/admin/dang-ky-tai-khoan")
	public String addAdmin() {

		return "admin/addAdmin";
	}

	@PostMapping("admin/dang-ky-thanh-cong")
	public String submitRegistration(@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("password") String password) throws SQLException {
		Admins ad1 = new Admins();
		ad1.setName(name);
		ad1.setUsername(username);
		ad1.setPassword(password);
		adminService.addAdmin(ad1);

		return "redirect:/admin/login";
	}

	@GetMapping("/admin/doanh-thu")
	public String doanhthu(HttpSession session, Model model) {
	    Admins admin = (Admins) session.getAttribute("admin");

	    if (admin == null) {
	        return "redirect:/admin/login";
	    }

	    return "/admin/doanhthu";
	}

	
	@GetMapping("/admin/ket-qua-doanh-thu")
	public String doanhthu(
	    @RequestParam(value = "date", required = false) String date,
	    @RequestParam(value = "month", required = false) Integer month,
	    @RequestParam(value = "year", required = false) Integer year,
	    Model model) {

	    List<Map<String, Object>> revenueList = new ArrayList<>();

	    // Kiểm tra tham số và tính toán doanh thu tương ứng
	    if (date != null && !date.isEmpty()) {
	        revenueList = adminService.getRevenueByDate(date); 
	    } else if (month != null && year != null) {
	        revenueList = adminService.getRevenueByMonth(year, month);
	    } else if (year != null) {
	        revenueList = adminService.getRevenueByYear(year); 
	    }

	    // Đổi tên các trường trong revenueList cho phù hợp với tên trong HTML
	    for (Map<String, Object> revenue : revenueList) {
	        revenue.put("bacSi", revenue.get("doctorName"));
	        revenue.put("doanhThu", revenue.get("totalRevenue"));
	        revenue.remove("doctorName");
	        revenue.remove("totalRevenue");
	    }

	    model.addAttribute("revenueList", revenueList);
	    return "/admin/doanhthu";
	}

	

	

}
