package com.iq3.plundr.security.jwt;

import com.iq3.plundr.security.UserPrincipalImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

	private final String SECRET_KEY = "Secret";

	public String generateToken(Authentication authentication) {

		UserPrincipalImpl userPrincipal = (UserPrincipalImpl) authentication.getPrincipal();

		Date currentDate = new Date();

		// Set 5 minute expiration for testing
		Date expirationDate = new Date(currentDate.getTime() + 300000);

		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(currentDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}

	public String getTokenUsername(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			log.error("Jwt error: {}", e.getMessage());
		}

		return false;
	}

}
