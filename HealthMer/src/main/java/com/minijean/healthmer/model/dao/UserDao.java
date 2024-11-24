package com.minijean.healthmer.model.dao;

import org.springframework.stereotype.Repository;

import com.minijean.healthmer.model.dto.User;

@Repository
public interface UserDao {
	void changePassword(String email, String newPassword);
	User findUser(String email, String newPassword);
}
