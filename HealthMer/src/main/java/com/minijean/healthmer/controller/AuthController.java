package com.minijean.healthmer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.minijean.healthmer.model.dto.User;
import com.minijean.healthmer.model.service.AuthService;
import com.minijean.healthmer.util.JwtUtil;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

//    @Autowired
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/register/email")
	public ResponseEntity<?> register(@RequestBody User user) {
		try {
			boolean isRegist = authService.register(user);
			if (isRegist) {
				return ResponseEntity
						// 201 Created
						.status(HttpStatus.CREATED).body(Map.of("message", "User registered successfully"));
			} else {
				return ResponseEntity
						// 400 Bad Request
						.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Email already exists"));
			}
		} catch (Exception e) {
			return ResponseEntity
					// 500 Internal Server Error
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("message", "Registr ation failed due to server error"));
		}
	}
	
	// 로그인
	@PostMapping("/login/email")
	public ResponseEntity<?> login(@RequestBody User user) {
	    HttpStatus status;
	    Map<String, Object> result = new HashMap<>();
	    String loginUserToken = authService.login(user);
	    if (loginUserToken != null) {
	        // 로그인 성공 시 메시지와 토큰 추가
	        result.put("message", "Login Successfully");
			result.put("access_token", loginUserToken);

	        status = HttpStatus.ACCEPTED;
	        // ResponseEntity에 헤더 추가
	        return ResponseEntity.status(status)
	                             .header("Authorization", "Bearer " + loginUserToken)
	                             .body(result);
	    } else {
	        // 로그인 실패 시 처리
	        result.put("message", "Login Failed");
	        status = HttpStatus.INTERNAL_SERVER_ERROR;

	        return ResponseEntity.status(status)
	                             .body(result);
	    }
	}

	// 로그아웃 (클라이언트 측에서 JWT 삭제)
	@PostMapping("/logout")
	public String logout() {
		// front 처리 
		return "User logged out successfully (Token removed on client side)";
	}
	
	// 탈퇴 delete
//	@PostMapping("/delete")
}