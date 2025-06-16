package org.sopt.global.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.sopt.global.exception.CustomException;
import org.sopt.global.exception.code.GlobalErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.token-validity-in-seconds}")
	private long tokenValidityInSeconds;

	private Key key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}

	public String createToken(Long userId) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + tokenValidityInSeconds * 1000);

		return Jwts.builder()
			.setSubject(userId.toString())
			.setIssuedAt(now)
			.setExpiration(validity)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}

	private Claims parseClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			throw new CustomException(GlobalErrorCode.EXPIRED_TOKEN);
		} catch (UnsupportedJwtException e) {
			throw new CustomException(GlobalErrorCode.UNSUPPORTED_TOKEN);
		} catch (MalformedJwtException e) {
			throw new CustomException(GlobalErrorCode.MALFORMED_TOKEN);
		} catch (SecurityException | IllegalArgumentException e) {
			throw new CustomException(GlobalErrorCode.INVALID_TOKEN);
		}
	}
}
