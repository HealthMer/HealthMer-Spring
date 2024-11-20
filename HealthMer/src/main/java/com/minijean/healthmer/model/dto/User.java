package com.minijean.healthmer.model.dto;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private Byte userTypeId;
    private Byte signUpRouteId;
    private String userId;
    private String password;
    private String email;
    private String nickname;
    private Byte age;
    private Byte genderId;
    private LocalDateTime createdAt;
    
    public User() {
	}
    
	public User(Long id, Byte userTypeId, Byte signUpRouteId, String userId, String password, String email,
			String nickname, Byte age, Byte genderId, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.userTypeId = userTypeId;
		this.signUpRouteId = signUpRouteId;
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.age = age;
		this.genderId = genderId;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Byte userTypeId) {
		this.userTypeId = userTypeId;
	}

	public Byte getSignUpRouteId() {
		return signUpRouteId;
	}

	public void setSignUpRouteId(Byte signUpRouteId) {
		this.signUpRouteId = signUpRouteId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Byte getAge() {
		return age;
	}

	public void setAge(Byte age) {
		this.age = age;
	}

	public Byte getGenderId() {
		return genderId;
	}

	public void setGenderId(Byte genderId) {
		this.genderId = genderId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userTypeId=" + userTypeId + ", signUpRouteId=" + signUpRouteId + ", userId="
				+ userId + ", password=" + password + ", email=" + email + ", nickname=" + nickname + ", age=" + age
				+ ", genderId=" + genderId + ", createdAt=" + createdAt + "]";
	}
}