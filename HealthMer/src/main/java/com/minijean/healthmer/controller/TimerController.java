package com.minijean.healthmer.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minijean.healthmer.model.dto.HealthCategory;
import com.minijean.healthmer.model.dto.Routine;
import com.minijean.healthmer.model.dto.Timer;
import com.minijean.healthmer.model.dto.TimerCategory;
import com.minijean.healthmer.model.dto.TimerRequest;
import com.minijean.healthmer.model.service.TimerService;
import com.minijean.healthmer.util.JwtUtil;

@RestController
@RequestMapping("/api/v1/timer")
public class TimerController {

	private final TimerService timerService;
	private final JwtUtil jtwUtil;

	public TimerController(TimerService timerService, JwtUtil jtwUtil) {
		this.timerService = timerService;
		this.jtwUtil = jtwUtil;
	}

	@GetMapping("")
	public ResponseEntity<?> listAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
		long userId = jtwUtil.extractUserId(authorizationHeader);
		List<Timer> list = timerService.getTimerList(userId);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}



	@GetMapping("/{id}")
	public ResponseEntity<?> oneTimer(@PathVariable("id") long id) {
		TimerRequest timerReq = timerService.getOneTimer(id);
		if (timerReq == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(timerReq, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateOneTimer(@PathVariable("id") long id, @RequestBody TimerRequest timerRequest) {

		TimerRequest updatedTimer = timerService.modifyOneTimer(id, timerRequest);

		if (updatedTimer == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 입력입니다.");
		}

		return ResponseEntity.ok(updatedTimer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTimer(@PathVariable("id") long id) {
		boolean isDeleted = timerService.removeTimer(id);

		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK).body("Timer deleted");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail Delete");
	}

	@GetMapping("/{id}/routine")
	public ResponseEntity<?> listRoutine(@PathVariable("id") long id) {
		List<Routine> list = timerService.getRoutineList(id);

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/category")
	public ResponseEntity<?> listCategory() {
		List<HealthCategory> list = timerService.getHealthCategoryList();

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}/category")
	public ResponseEntity<?> listCategory(@PathVariable("id") long id) {
		List<TimerCategory> list = timerService.getCategoryList(id);

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Timer> create(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody TimerRequest timerRequest) {
		long userId = jtwUtil.extractUserId(authorizationHeader);
		Timer timer = timerRequest.getTimer();
		timer.setUserId(userId);
		timerRequest.setTimer(timer);
		
		Timer createdTimer = timerService.createTimer(timerRequest);

		return new ResponseEntity<>(createdTimer, HttpStatus.CREATED);
	}
}
