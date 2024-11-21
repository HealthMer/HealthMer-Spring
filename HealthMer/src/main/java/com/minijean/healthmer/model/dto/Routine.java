package com.minijean.healthmer.model.dto;

public class Routine {
	private long id;
	private long timerInfoId;
	private String name;
	private short time;
	private short isRest;
	
	public Routine() {

	}

	public Routine(long id, long timerInfoId, String name, short time, short isRest) {
		super();
		this.id = id;
		this.timerInfoId = timerInfoId;
		this.name = name;
		this.time = time;
		this.isRest = isRest;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTimerInfoId() {
		return timerInfoId;
	}

	public void setTimerInfoId(long timerInfoId) {
		this.timerInfoId = timerInfoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getTime() {
		return time;
	}

	public void setTime(short time) {
		this.time = time;
	}

	public short getIsRest() {
		return isRest;
	}

	public void setIsRest(short isRest) {
		this.isRest = isRest;
	}

	@Override
	public String toString() {
		return "Routine [id=" + id + ", timerInfoId=" + timerInfoId + ", name=" + name + ", time=" + time + ", isRest="
				+ isRest + "]";
	}
	
}
