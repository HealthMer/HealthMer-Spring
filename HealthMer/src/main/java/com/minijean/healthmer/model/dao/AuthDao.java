package com.minijean.healthmer.model.dao;

import org.springframework.stereotype.Repository;

import com.minijean.healthmer.model.dto.User;

@Repository
public interface AuthDao {
	User findByEmail(String eamil);
	User findByUserId(String id);
	User findByNickname(String nickname);
	void registUser(User user);
}
