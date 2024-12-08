package com.example.bookingcare.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final JavaMailSender mailSender;

	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Gửi email xác nhận thông qua JavaMailSender
	 *
	 * @param userEmail    - Email người nhận
	 * @param subject      - Tiêu đề email
	 * @param emailContent - Nội dung email
	 */
	public void sendConfirmationEmail(String userEmail, String subject, String emailContent) {
		try {
			// Tạo email
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(userEmail); // Email người nhận
			message.setSubject(subject); // Tiêu đề email
			message.setText(emailContent); // Nội dung email
			message.setFrom("viethoangxtpro08@gmail.com"); // Thay thế bằng email của bạn

			// Gửi email
			mailSender.send(message);
			System.out.println("Email gửi thành công đến: " + userEmail);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Lỗi khi gửi email: " + e.getMessage());
		}
	}
}
