package com.minijean.healthmer.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.minijean.healthmer.model.dao.UserDao;
import com.minijean.healthmer.model.dto.User;

@Service
public class UserService {
	
	private final UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	public User getUserById(Long id) {
		return null;
//		return userMapper.findById(id);
	}

	public User createUser(User user) {
		return null;
//		userMapper.insert(user);
//		return user;
	}

	public User updateUser(User user) {
		return null;
//		userMapper.update(user);
//		return user;
	}

	public void deleteUser(Long id) {
//		userMapper.delete(id);
	}
}
