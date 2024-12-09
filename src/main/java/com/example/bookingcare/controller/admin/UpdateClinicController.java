package com.example.bookingcare.controller.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookingcare.model.Clinic;
import com.example.bookingcare.service.doctor.ClinicServiceImpl;

@Controller
public class UpdateClinicController {

	@Autowired
	private ClinicServiceImpl clinicService;

	@GetMapping("/admin/sua-csyt")
	public String adminDetail(@RequestParam("id") int id, Model model) {
		Clinic Clinic = clinicService.getClinicById(id);
		model.addAttribute("clinic", Clinic);
		return "admin/updateClinic";
	}

////"/submit_update"
//
	@PostMapping("/admin/sua-csyt/submit_update")
	public String submitUpdate(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("address") String address,
			@RequestParam("workingTime") String workingTime) throws SQLException {

		// Lấy cơ sở y tế hiện tại theo id
		Clinic clinic = clinicService.getClinicById(id);

		// Cập nhật thông tin cơ sở y tế
		clinic.setName(name);
		clinic.setDescription(description);
		clinic.setAddress(address);
		clinic.setWorkingTime(workingTime);

		// Lưu lại cơ sở y tế sau khi cập nhật
		clinicService.updateClinic(clinic);

		// Chuyển hướng về trang danh sách cơ sở y tế sau khi cập nhật
		return "redirect:/admin/quan-ly-csyt";
	}

}
