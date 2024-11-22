package com.minijean.healthmer.model.dao;

import org.springframework.stereotype.Repository;

import com.minijean.healthmer.model.dto.User;


@Repository
public interface AuthDao {
//	User 
	
	User findByEmail(String eamil);
	User findByUserId(String id);
	void registUser(User user);
}
