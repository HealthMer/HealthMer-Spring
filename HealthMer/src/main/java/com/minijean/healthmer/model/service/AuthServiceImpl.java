package com.minijean.healthmer.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minijean.healthmer.model.dao.AuthDao;
import com.minijean.healthmer.model.dto.User;
import com.minijean.healthmer.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

	private final AuthDao authDao;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

	public AuthServiceImpl(AuthDao authDao, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
		this.authDao = authDao;
		this.jwtUtil = jwtUtil;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public boolean register(User user) {
		User findUserForEmail = authDao.findByEmail(user.getEmail());
		User findUserForNickname = authDao.findByNickname(user.getNickname());
		
		if (findUserForEmail == null && findUserForNickname == null) {
			user.setUserTypeId((byte) 2);
			user.setSignUpRouteId((byte) 5);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			authDao.registUser(user);
			
			User data = authDao.findByEmail(user.getEmail());
			
			if (data == null) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String login(User user) {
	    User findedUser = authDao.findByEmail(user.getEmail());
        if (user == null || !passwordEncoder.matches(user.getPassword(), findedUser.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return jwtUtil.createToken(findedUser.getEmail());
	}

	@Override
	public boolean logout(String token) {
		// 클라에서 토큰 처리
		return true;
	}

	@Override
	public boolean changePassword(String email, String oldPassword, String newPassword) {
		return false;
	}

	@Override
	public boolean validateToken(String token) {
		return false;
	}

	@Override
	public String verifyUser(String token) {
		return null;
	}
}