package com.api.student_management.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.api.student_management.security.CustomUserDetails;
import com.api.student_management.security.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

	@Value("${app.jwt.secret}")
	private String SECRET_KEY;

	public String generateAccessToken(String email) {
		CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);
		String authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
		return Jwts.builder()
				.setSubject(String.format(email))
				.claim("roles", authorities)
				.setIssuer("CngPhm")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException ex) {
			LOGGER.error("JWT expired", ex.getMessage());
		} catch (IllegalArgumentException ex) {
			LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
		} catch (MalformedJwtException ex) {
			LOGGER.error("JWT is invalid", ex);
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("JWT is not supported", ex);
		} catch (SignatureException ex) {
			LOGGER.error("Signature validation failed");
		}
		return false;
	}
	
	private Claims parseClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}

	public Date getIssuedAtDate(String token) {
		return parseClaims(token).getIssuedAt();
	}

	public Date getExpirationDate(String token) {
		return parseClaims(token).getExpiration();
	}

	private Boolean isAliveToken(String token) {
		final Date expiration = getExpirationDate(token);
		return expiration.after(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getSubject(token);
        return (userDetails != null && username.equals(userDetails.getUsername()) && isAliveToken(token));
    }
	
}