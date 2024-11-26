package com.minijean.healthmer.model.dao;

import org.springframework.stereotype.Repository;

import com.minijean.healthmer.model.dto.EmailVerificationToken;

@Repository
public interface EmailDao {
	 void insertToken(EmailVerificationToken emailVerificationToken);
	 EmailVerificationToken findByToken(String token);
	 void deleteByToken(String token);
}
