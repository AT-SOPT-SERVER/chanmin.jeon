package org.sopt.global.common.jwt;

import java.security.Key;
import java.util.Date;

import org.sopt.global.common.exception.CustomException;
import org.sopt.global.common.exception.code.GlobalErrorCode;
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
	private long tokenVAlidityInSeconds;

	private Key key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
	}

	public String createToken(Long userId) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + tokenVAlidityInSeconds * 1000);

		return Jwts.builder()
			.setSubject(userId.toString())
			.setIssuedAt(now)
			.setExpiration(validity)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public Long getUserId(String token) {
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJwt(token)
			.getBody();

		return Long.parseLong(claims.getSubject());

	}

	public void validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
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
