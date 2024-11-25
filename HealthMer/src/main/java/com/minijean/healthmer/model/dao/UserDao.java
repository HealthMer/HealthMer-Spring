package com.minijean.healthmer.model.dao;

import org.springframework.stereotype.Repository;

import com.minijean.healthmer.model.dto.User;

@Repository
public interface UserDao {
	User findUser(String email, String newPassword);
	void changePassword(String email, String newPassword);
	void changeNickname(String email, String nickname);
	void changeAge(String email, byte age);
	void changeGender(String email, byte genderId);
}

