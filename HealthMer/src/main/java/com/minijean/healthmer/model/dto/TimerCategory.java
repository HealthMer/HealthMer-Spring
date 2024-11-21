package com.minijean.healthmer.model.dto;

public class TimerCategory {
	private long timerInfoId;
	private long healthCategoryId;
	
	public TimerCategory() {

	}

	public TimerCategory(long timerInfoId, long healthCategoryId) {
		super();
		this.timerInfoId = timerInfoId;
		this.healthCategoryId = healthCategoryId;
	}

	public long getTimerInfoId() {
		return timerInfoId;
	}

	public void setTimerInfoId(long timerInfoId) {
		this.timerInfoId = timerInfoId;
	}

	public long getHealthCategoryId() {
		return healthCategoryId;
	}

	public void setHealthCategoryId(long healthCategoryId) {
		this.healthCategoryId = healthCategoryId;
	}

	@Override
	public String toString() {
		return "TimerCategory [timerInfoId=" + timerInfoId + ", healthCategoryId=" + healthCategoryId + "]";
	}
	
}
