package com.minijean.healthmer.model.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.minijean.healthmer.model.dao.EmailDao;
import com.minijean.healthmer.model.dto.EmailVerificationToken;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private JavaMailSender mailSender;
    private EmailDao emailDao;
    
    public EmailService(JavaMailSender mailSender, EmailDao emailDao) {
		this.mailSender = mailSender;
		this.emailDao = emailDao;
	}

	public boolean register(String email) {
		// 1. 인증 토큰 생성
    	String token = UUID.randomUUID().toString();
    	
		// 2. 토큰을 DB에 저장
    	EmailVerificationToken evt = new EmailVerificationToken(null, token, email);
    	emailDao.insertToken(evt);
    	
    	
    	//저장 확인 
    	if (emailDao.findByToken(token) == null) {
    		//저장 실패 
    		System.out.println("실팬가 ? ");
    		return false;
    	}
    	
    	// 3. 인증 이메일 전송
		try {
			System.out.println("여긴가?");
			sendVerificationEmail(email, token);
			System.out.println("why?");
		} catch (MessagingException e) {
			return false;
		}
    	
    	return true;
    }
    
    public boolean verify(String token) {
    	
    	if (emailDao.findByToken(token) == null) {
    		return false;
    	}
    	
    	emailDao.deleteByToken(token);
    	
    	return true;
    }
    
    //이메일 인증 request
	private void sendVerificationEmail(String toEmail, String token) throws MessagingException {
		String subject = "Email Verification";
		String verificationUrl = "http://localhost:8080/api/v1/email/verify?token=" + token;
		String content = "<p>Click the link below to verify your email:</p>" + "<a href=\"" + verificationUrl
				+ "\">Verify Now</a>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(toEmail);
		helper.setSubject(subject);
		helper.setText(content, true);

		mailSender.send(message);
	}
}