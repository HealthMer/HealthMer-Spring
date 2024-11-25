package com.minijean.healthmer.model.dto;

public class ChangePasswordRequest {
    private String email;
    private String newPassword;
    
	public ChangePasswordRequest(String email, String newPassword) {
		this.email = email;
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ChangePasswordRequest [email=" + email + ", newPassword=" + newPassword + "]";
	}
}