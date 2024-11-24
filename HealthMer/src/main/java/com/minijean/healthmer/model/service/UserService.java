package com.minijean.healthmer.model.service;

import com.minijean.healthmer.model.dto.ChangePasswordRequest;
import com.minijean.healthmer.model.dto.User;

public interface UserService {

    /**
     * 비밀번호를 변경합니다.
     * @param User 사용자 정보 
     * @return 변경 성공 여부
     */
    boolean changePassword(ChangePasswordRequest changePasswordRequest);

    /**
     * 사용자 프로필을 업데이트합니다.
     * @param User 사용자 정보 
     * @param updatedUser 업데이트된 사용자 정보
     * @return 업데이트된 사용자 객체
     */
    User updateProfile(User user);

    /**
     * 사용자 정보를 조회합니다.
     * @param User 사용자 정
     * @return 사용자 정보 객체
     */
    User getUserInfo(User user);
}

/*
 *nickname
 *age
 *genderId
 * */