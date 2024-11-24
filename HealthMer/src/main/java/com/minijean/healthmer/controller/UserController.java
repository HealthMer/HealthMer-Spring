package com.minijean.healthmer.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minijean.healthmer.model.dto.ChangePasswordRequest;
import com.minijean.healthmer.model.dto.User;
import com.minijean.healthmer.model.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    //Changing Password
    // Body -> beforePassword, 
    @PostMapping("update/password")
    public ResponseEntity<?> postMethodName(@RequestBody ChangePasswordRequest cpr) {
    	Map<String, Object> result = new HashMap<>();
    	String email = cpr.getEmail();
    	String newPassword = cpr.getNewPassword();
    	
		if (email == null || newPassword == null) {
			result.put("status", HttpStatus.BAD_REQUEST.value());
			result.put("message", "Email and new password are required.");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
    	
		boolean isPasswordChanged = userService.changePassword(cpr);
	    if (!isPasswordChanged) {
			// Fail To Change
	        result.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        result.put("message", "Failed to update the password. Please try again later.");
	        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	    result.put("status", HttpStatus.OK.value());
	    result.put("message", "Password updated successfully.");
		
    	return new ResponseEntity<>(result, HttpStatus.OK) ;
    }
    
	// email필수, null로 들어오면 변경하지 않음.
    @PostMapping("/update/profile")
	public ResponseEntity<?> profile(@RequestBody User user) {
    	Map<String, Object> result = new HashMap<>();
    	
    	if (user == null) {
			result.put("status", HttpStatus.BAD_REQUEST.value());
			result.put("message", "Email and new changing Profile data are required.");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    	}
    	
		boolean isUpdated = userService.updateProfile(user);
		
	    if (!isUpdated) {
			// Fail To Change
	        result.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        result.put("message", "Failed to update the password. Please try again later.");
	        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	    result.put("status", HttpStatus.OK.value());
	    result.put("message", "Password updated successfully.");
		
    	return new ResponseEntity<>(result, HttpStatus.OK) ;
	}
}

