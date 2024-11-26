package com.minijean.healthmer.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.minijean.healthmer.model.dto.EmailVerificationToken;
import com.minijean.healthmer.model.service.EmailService;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

	private final EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/register")
	public String registerUser(@RequestBody EmailVerificationToken evt) {

		if (!emailService.register(evt.getEmail())) {
			return "Verification email sent Fail.";
		} 

		return "Verification email sent successfully.";
	}

	@GetMapping("/verify")
	public String verifyEmail(@RequestParam("token") String token) {
		System.out.println(token);
        if (!emailService.verify(token)) {
            return "Invalid token. Please retry";
        }

		// 만료 시간을 확인하고 추가 로직을 구현할 수 있습니다.
		// 예: 사용자의 이메일을 인증된 상태로 업데이트

		return "Email verified successfully.";
	}
}