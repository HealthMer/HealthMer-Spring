package com.minijean.healthmer.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class BearerTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");

        // Bearer 토큰 확인
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing or invalid Authorization header");
            return false;
        }

        // Bearer 이후의 토큰 추출
        String token = authorizationHeader.substring(7);

        // 토큰 유효성 검사 (예: JWT 검증)
        if (!isValidToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid Token");
            return false;
        }

        // 토큰이 유효하면 요청을 계속 처리
        return true;
    }

    // JWT 검증 또는 기타 토큰 유효성 검사 로직 구현
    private boolean isValidToken(String token) {
        // 예를 들어, JWT 유효성 검사를 구현할 수 있음
        // 이 부분은 JWT 라이브러리나 커스텀 검증 로직을 사용하여 구현
        return true; // 실제 검증 로직을 적용해야 함
    }
}
