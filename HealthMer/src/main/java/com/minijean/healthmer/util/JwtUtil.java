package com.minijean.healthmer.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.minijean.healthmer.model.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
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
	
	// SecretKey 생성
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT에서 사용자 ID 추출
    public Long extractUserId(String token) {
//    	System.out.println("here1");
//    	System.out.println(token);
//    	System.out.println(token.replace("Bearer ", "").trim());
    	
    	Jws<Claims> jwsClaims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token.replace("Bearer ", "").trim());
//    	System.out.println(jwsClaims.getHeader());
//    	System.out.println(jwsClaims.getPayload());
//    	System.out.println(jwsClaims.getPayload().get("id", Long.class));
    	
    	return jwsClaims.getPayload().get("id", Long.class);
    }
    
//    public static Claims validateToken(String token) {
//        if (JwtBlacklist.isBlacklisted(token)) {
//            throw new IllegalArgumentException("Token is blacklisted");
//        }
//
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//    }
}