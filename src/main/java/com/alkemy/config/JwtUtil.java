package com.alkemy.config;

import static java.util.Collections.emptyList;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	public static void addAuthentication(HttpServletResponse response, String username) {
		String token = Jwts.builder().setSubject(username)

				.setExpiration(new Date(System.currentTimeMillis() + 60000))
				.signWith(SignatureAlgorithm.HS512, "P@tit0").compact();
		response.addHeader("Authorization", "Bearer " + token);
	}

	public static Authentication getAuthentication(HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		if (token != null) {
			String user = Jwts.parser().setSigningKey("P@tit0").parseClaimsJws(token.replace("Bearer", "")).getBody()
					.getSubject();

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
		}
		return null;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setSubject(subject).setExpiration(new Date(System.currentTimeMillis() + 60000))
				.signWith(SignatureAlgorithm.HS512, "P@tit0").compact();
	}

}
