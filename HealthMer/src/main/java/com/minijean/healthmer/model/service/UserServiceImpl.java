package com.minijean.healthmer.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minijean.healthmer.model.dao.AuthDao;
import com.minijean.healthmer.model.dao.UserDao;
import com.minijean.healthmer.model.dto.ChangePasswordRequest;
import com.minijean.healthmer.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	private final AuthDao authDao;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserDao userDao, AuthDao authDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.authDao = authDao;
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
	@Transactional
	public boolean updateProfile(User user) {
		String email = user.getEmail();
		String changingNickname = user.getNickname();
		Byte changingAge = user.getAge();
		Byte changingGenderId = user.getGenderId();
		
		if (changingNickname != null) {
			userDao.changeNickname(email, changingNickname);
		}
		
		if (changingAge != null) {
			userDao.changeAge(email, changingAge);
		}
		
		if (changingGenderId != null) {
			userDao.changeGender(email, changingGenderId);
		}
		
		User changedUser = authDao.findByEmail(email);
		
	    if (changingNickname != null && !changingNickname.equals(changedUser.getNickname())) {
	        return false;
	    }

	    if (changingAge != null && !changingAge.equals(changedUser.getAge())) {
	        return false;
	    }

	    if (changingGenderId != null && !changingGenderId.equals(changedUser.getGenderId())) {
	        return false;
	    }
		
		return true;
	}

	@Override
	public User getUserInfo(User user) {
		// TODO Auto-generated method stub  
		return null;
	}
}
