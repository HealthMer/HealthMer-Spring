package com.minijean.healthmer.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.minijean.healthmer.model.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String key = "SSAFY_NonMajor_JavaTrack_SecretKey";
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

	public String createTokenBy(User user) {
		Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 6);
		return Jwts.builder().header().add("typ", "JWT").and().claim("id", user.getId()).claim("email", user.getEmail())
				.expiration(exp).signWith(secretKey).compact();
	}

//	public String createLoginToken(String name) {
//		Date exp = new Date(System.currentTimeMillis()+ 1000*60*60*6);
//		return Jwts.builder().header().add("typ", "JWT").and()
//				.claim("name", name).expiration(exp)
//				.signWith(secretKey).compact();
//	}

	public Jws<Claims> validate(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
	}

    public String extractUserId(String token) {
    	// getBody() 메서드가 deprecated된 경우 대안은 라이브러리의 최신 문서를 참조하여 새로운 API로 업데이트된 방식에 맞게 코드를 작성하는 것입니다. 하지만, 현재의 최신 JJWT 라이브러리에서는 위의 방식이 유효하고 권장되는 패턴입니다.
        Claims claims = validate(token).getBody(); // 클레임을 추출하는 권장되는 방법 사용
        return claims.get("id", String.class); // "id" 클레임에서 사용자 ID 추출
    }
}