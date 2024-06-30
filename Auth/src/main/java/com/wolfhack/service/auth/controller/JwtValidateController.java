package com.wolfhack.service.auth.controller;

import com.wolfhack.service.auth.model.dto.UserAuthorityInfo;
import com.wolfhack.service.auth.service.JwtSigner;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jwt/validate")
@RequiredArgsConstructor
public class JwtValidateController {

	private final JwtSigner jwtSigner;

	@GetMapping
	public Mono<UserAuthorityInfo> validate(@RequestParam("token") String token) {
		return jwtSigner.validateAndReturnInfo(token);
	}

}
