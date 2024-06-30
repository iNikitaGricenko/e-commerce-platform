package com.wolfhack.service.user.controller;

import com.wolfhack.service.user.mapper.UserMapper;
import com.wolfhack.service.user.model.domain.User;
import com.wolfhack.service.user.model.dto.UserRegisterDTO;
import com.wolfhack.service.user.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

	private final RegistrationService registrationService;

	private final UserMapper userMapper;

	@PostMapping
	public long register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
		User model = userMapper.toModel(userRegisterDTO);
		return registrationService.register(model);
	}

}
