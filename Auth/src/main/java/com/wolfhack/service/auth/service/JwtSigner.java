package com.wolfhack.service.auth.service;

import com.wolfhack.service.auth.client.UserManagementClient;
import com.wolfhack.service.auth.exception.ForbiddenException;
import com.wolfhack.service.auth.model.User;
import com.wolfhack.service.auth.model.dto.UserAuthorityInfo;
import com.wolfhack.service.auth.model.dto.UserTokenResponseDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.KeyPair;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtSigner {

	private final static KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
	private final UserManagementClient userManagementClient;

	public String create(String username) {
		return Jwts.builder()
			.signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
			.setSubject(String.valueOf(username))
			.setIssuer("com.wolfhack")
			.setExpiration(
				Date.from(
					Instant
						.now()
						.plus(Duration.ofHours(1))
				)
			)
			.setIssuedAt(
				Date.from(Instant.now())
			)
			.compact();
	}

	public Jws<Claims> validate(String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(keyPair.getPrivate())
			.build()
			.parseClaimsJws(token);
	}

	public Mono<UserAuthorityInfo> validateAndReturnInfo(String token) {
		try {
			Mono<UserTokenResponseDTO> userTokenResponseDTO = userManagementClient.findByToken(token);

			if (userTokenResponseDTO != null) {
				return userTokenResponseDTO
					.filter(Objects::nonNull)
					.map(UserTokenResponseDTO::id)
					.flatMap(userManagementClient::findById)
					.map(this::toAuthorityInfo);
			}

			return userManagementClient.findByUsername(
					validate(token)
						.getBody()
						.getSubject()
				)
				.map(this::toAuthorityInfo);
		} catch (MalformedJwtException | SecurityException exception) {
			throw new ForbiddenException("Token invalid");
		} catch (ExpiredJwtException exception) {
			throw new ForbiddenException("Token expired");
		}
	}

	private UserAuthorityInfo toAuthorityInfo(User user) {
		return new UserAuthorityInfo(
			user.getId(),
			user.getUsername(),
			List.of(
				user.getRole().getRoleName()
			)
		);
	}

}
