package com.minijean.healthmer.model.dto;

import java.util.List;

public class TimerRequest {
	private Timer timer;
	private List<Routine> routines;
	private List<TimerCategory> timerCategories;
	
	public TimerRequest() {
		
	}

	public TimerRequest(Timer timer, List<Routine> routines, List<TimerCategory> timerCategories) {
		super();
		this.timer = timer;
		this.routines = routines;
		this.timerCategories = timerCategories;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public List<Routine> getRoutines() {
		return routines;
	}

	public void setRoutines(List<Routine> routines) {
		this.routines = routines;
	}

	public List<TimerCategory> getTimerCategories() {
		return timerCategories;
	}

	public void setTimerCategories(List<TimerCategory> timerCategories) {
		this.timerCategories = timerCategories;
	}

	@Override
	public String toString() {
		return "TimerRequest [timer=" + timer + ", routines=" + routines + ", timerCategories=" + timerCategories + "]";
	}
	
}
