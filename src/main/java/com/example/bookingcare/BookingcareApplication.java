package com.example.bookingcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.example.bookingcare")
public class BookingcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingcareApplication.class, args);
	}

}
