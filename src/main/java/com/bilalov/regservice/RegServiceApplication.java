package com.bilalov.regservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RegServiceApplication {

	public RegServiceApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(RegServiceApplication.class, args);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
		String hashedAdmin = passwordEncoder.encode("admin");
		String hashedPassword = passwordEncoder.encode("111");
		System.out.println(hashedPassword);
	}

}
