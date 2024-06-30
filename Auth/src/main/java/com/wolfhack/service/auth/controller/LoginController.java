package com.wolfhack.service.auth.controller;

import com.google.common.net.HttpHeaders;
import com.wolfhack.service.auth.client.UserManagementClient;
import com.wolfhack.service.auth.model.dto.UserLogin;
import com.wolfhack.service.auth.service.JwtSigner;
import com.wolfhack.service.auth.service.PBKDF2Encoder;
import com.wolfhack.service.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;

	private final PBKDF2Encoder pbkdf2Encoder;

	private final JwtSigner jwtSigner;

	private final UserManagementClient userManagementClient;

	@PostMapping
	public Mono<ResponseEntity<Object>> login(@RequestBody UserLogin userLogin) {
		return userService.get(userLogin.username())
			.filter(
				user -> userManagementClient
					.login(userLogin)
					.equals(
						Mono.just(Boolean.TRUE)
					)
			)
			.map(
				user -> ResponseEntity
					.noContent()
					.header(
						HttpHeaders.AUTHORIZATION,
						jwtSigner.create(user.getUsername())
					)
					.build()
			)
			.switchIfEmpty(
				Mono.just(
					ResponseEntity
						.status(HttpStatus.FORBIDDEN)
						.build()
				)
			);
	}

}
