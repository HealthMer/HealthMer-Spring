package com.minijean.healthmer.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minijean.healthmer.model.dto.Timer;
import com.minijean.healthmer.model.service.CategorySelectingService;
import com.minijean.healthmer.util.JwtUtil;

@RestController
@RequestMapping("/api/v1/category")
public class CategorySelectingController {

    private CategorySelectingService categorySelectingService;
    private JwtUtil jwtUtil;
    
    public CategorySelectingController(CategorySelectingService categorySelectingService, JwtUtil jwtUtil) {
		this.categorySelectingService = categorySelectingService;
		this.jwtUtil = jwtUtil;
	}

//	@GetMapping("/{categoryId}/timers")
//    @GetMapping("/{categoryId}")
//    public ResponseEntity<?> getTimersByCategory(@PathVariable("categoryId") int categoryId) {
//        if (categoryId < 1 || categoryId > 6) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid categoryId. Please provide a value between 1 and 6.");
//        }
//        System.out.println(categoryId);
//        try {
//            List<Timer> timers = categorySelectingService.getTimersByCategory(categoryId);
//            System.out.println(timers);
//            if (timers.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//            }
//            return ResponseEntity.ok(timers);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching timers.");
//        }
//    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getTimersByCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable("categoryId") int categoryId) {
        if (categoryId < 1 || categoryId > 6) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid categoryId. Please provide a value between 1 and 6.");
        }
        try {
            long userId = jwtUtil.extractUserId(authorizationHeader);
            System.out.println(userId);
            List<Timer> timers = categorySelectingService.getTimersByCategory(userId, categoryId);
            if (timers.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(timers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching timers.");
        }
    }
}