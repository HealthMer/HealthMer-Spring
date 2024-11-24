package com.minijean.healthmer.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.minijean.healthmer.model.dao.UserDao;
import com.minijean.healthmer.model.dto.ChangePasswordRequest;
import com.minijean.healthmer.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public boolean changePassword(ChangePasswordRequest changePasswordRequest) {
		String email = changePasswordRequest.getEmail();
		String newPassword = changePasswordRequest.getNewPassword();
		String decodedNewPassword = passwordEncoder.encode(newPassword);
		
		userDao.changePassword(email, decodedNewPassword);
		
		User dbUser = userDao.findUser(email, decodedNewPassword);
		if (dbUser == null) {
			return false;
		} else if (passwordEncoder.matches(newPassword, dbUser.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User updateProfile(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserInfo(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
