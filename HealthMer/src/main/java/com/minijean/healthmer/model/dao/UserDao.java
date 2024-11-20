package com.minijean.healthmer.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.minijean.healthmer.model.dto.User;

@Repository
public interface UserDao {
	public List<User> findAll();
	
}
