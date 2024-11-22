package com.minijean.healthmer.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String key = "SSAFY_NonMajor_JavaTrack_SecretKey";
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
	
	public String createToken(String name) {
		Date exp = new Date(System.currentTimeMillis()+ 1000*60*60*6);
		return Jwts.builder().header().add("typ", "JWT").and()
				.claim("name", name).expiration(exp)
				.signWith(secretKey).compact();
	}
	
	public Jws<Claims> vaildate(String token){
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
	}
}