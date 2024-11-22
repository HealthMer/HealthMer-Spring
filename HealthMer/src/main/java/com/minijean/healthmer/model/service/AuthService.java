package com.minijean.healthmer.model.service;

import com.minijean.healthmer.model.dto.User;

public interface AuthService {

    /**
     * 사용자 회원가입
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return 회원가입 성공 여부
     */
	boolean register(String email, String password);
	
	boolean register(User user);
	
    /**
     * 사용자 로그인
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return JWT 토큰
     * @throws IllegalArgumentException 로그인 실패 시 예외
     */
    String login(String email, String password);

    /**
     * 사용자 로그아웃
     * @param token 클라이언트가 제공한 JWT 토큰
     * @return 로그아웃 성공 여부
     */
    boolean logout(String token);

    /**
     * 비밀번호 변경
     * @param email 사용자 이메일
     * @param oldPassword 기존 비밀번호
     * @param newPassword 새 비밀번호
     * @return 비밀번호 변경 성공 여부
     * @throws IllegalArgumentException 비밀번호 변경 실패 시 예외
     */
    boolean changePassword(String email, String oldPassword, String newPassword);

    /**
     * 토큰 검증
     * @param token 클라이언트가 제공한 JWT 토큰
     * @return 토큰이 유효하면 true, 그렇지 않으면 false
     */
    boolean validateToken(String token);

    /**
     * 사용자 확인
     * @param token 클라이언트가 제공한 JWT 토큰
     * @return 사용자의 이메일
     */
    String verifyUser(String token);
}