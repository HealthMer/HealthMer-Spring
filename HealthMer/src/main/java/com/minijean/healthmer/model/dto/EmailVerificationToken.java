package com.minijean.healthmer.model.dto;

public class EmailVerificationToken {
	private Long id;
	private String token;
	private String email;
	
	public EmailVerificationToken(Long id, String token, String email) {
		this.id = id;
		this.token = token;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmailVerificationToken [id=" + id + ", token=" + token + ", email=" + email + "]";
	}
}