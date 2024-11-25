package com.minijean.healthmer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.minijean.healthmer.interceptor.BearerTokenInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private BearerTokenInterceptor bearerTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bearerTokenInterceptor)
                .addPathPatterns("/api/v1/**") // 토큰 검증이 필요한 경로 설정
                .excludePathPatterns("/api/v1/auth/login/email", "/api/v1/auth/register/email"); // 인증 관련 경로 제외 (로그인, 회원가입 등)
    }
}