package com.wolfhack.service.auth.controller;

import com.wolfhack.service.auth.mapper.UserMapper;
import com.wolfhack.service.auth.model.dto.UserRegistration;
import com.wolfhack.service.auth.service.PBKDF2Encoder;
import com.wolfhack.service.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

	private final UserService userService;

	private final PBKDF2Encoder pbkdf2Encoder;

	private final UserMapper userMapper;

	@PostMapping
	public Mono<Long> register(@RequestBody Mono<UserRegistration> userRegistration) {
		return userService.save(userRegistration);
	}

}
