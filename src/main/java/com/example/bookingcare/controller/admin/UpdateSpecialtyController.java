package com.example.bookingcare.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bookingcare.model.Specialty;
import com.example.bookingcare.service.doctor.SpecialtyServiceImpl;

@Controller
public class UpdateSpecialtyController {
	@Autowired
	private SpecialtyServiceImpl specialtyService;

	@GetMapping("/admin/sua-chuyen-khoa")
	public String adminDetail(@RequestParam("id") int id, Model model) {
		Specialty specialty = specialtyService.getSpecialtyById(id);
		model.addAttribute("specialty", specialty);
		return "admin/updateSpecialty";
	}

////"/submit_update"
//
	@PostMapping("/admin/sua-chuyen-khoa/submit_update")
	public String submitUpdate(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("logoSpecialty") MultipartFile logoSpecialty)
			throws SQLException {

		// Lấy chuyên khoa hiện tại theo id
		Specialty specialty = specialtyService.getSpecialtyById(id);
		// Cập nhật thông tin chuyên khoa
		specialty.setName(name);
		specialty.setDescription(description);

		// Kiểm tra nếu logo được upload
		if (!logoSpecialty.isEmpty()) {
			// Lưu logo vào thư mục hoặc xử lý theo cách khác
			String logoFileName = specialtyService.saveLogo(logoSpecialty); // Tạo phương thức để lưu logo
			specialty.setLogoSpecialty(logoFileName); // Lưu tên file hoặc URL
		}

		// Lưu lại chuyên khoa sau khi cập nhật
		specialtyService.updateSpecialty(specialty);

		// Chuyển hướng về trang danh sách chuyên khoa sau khi cập nhật
		return "redirect:/admin/quan-ly-chuyen-khoa";
	}

}
