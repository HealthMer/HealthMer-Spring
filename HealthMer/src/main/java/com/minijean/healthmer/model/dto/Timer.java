package com.minijean.healthmer.model.dto;

public class Timer {
	private long id;
	private long userId;
	private String title;
	private long viewsCount = 0;
	private long completeCount = 0;
	private int level;
	private String createdAt;
	private long totalRoutineTime;

	public Timer() {
		super();
	}

	public Timer(long id, long userId, String title, long viewsCount, long completeCount, int level, String createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.viewsCount = viewsCount;
		this.completeCount = completeCount;
		this.level = level;
		this.createdAt = createdAt;
		this.totalRoutineTime = totalRoutineTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getViewsCount() {
		return viewsCount;
	}

	public void setViewsCount(long viewsCount) {
		this.viewsCount = viewsCount;
	}

	public long getCompleteCount() {
		return completeCount;
	}

	public void setCompleteCount(long completeCount) {
		this.completeCount = completeCount;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public long getTotalRoutineTime() {
		return totalRoutineTime;
	}

	public void setTotalRoutineTime(long totalRoutineTime) {
		this.totalRoutineTime = totalRoutineTime;
	}

	@Override
	public String toString() {
		return "Timer [id=" + id + ", userId=" + userId + ", title=" + title + ", viewsCount=" + viewsCount
				+ ", completeCount=" + completeCount + ", level=" + level + ", createdAt=" + createdAt
				+ ", totalRoutineTime=" + totalRoutineTime + "]";
	}

}
