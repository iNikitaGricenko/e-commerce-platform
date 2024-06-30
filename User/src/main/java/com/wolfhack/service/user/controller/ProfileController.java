package com.wolfhack.service.user.controller;

import com.wolfhack.service.user.mapper.UserMapper;
import com.wolfhack.service.user.mapper.UserTokenMapper;
import com.wolfhack.service.user.model.domain.User;
import com.wolfhack.service.user.model.domain.UserToken;
import com.wolfhack.service.user.model.dto.UserLoginDTO;
import com.wolfhack.service.user.model.dto.UserLoginResponseDTO;
import com.wolfhack.service.user.model.dto.UserProfileEditDTO;
import com.wolfhack.service.user.model.dto.UserTokenResponseDTO;
import com.wolfhack.service.user.service.ProfileManagement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileManagement profileManagement;

	private final UserTokenMapper userTokenMapper;

	private final UserMapper userMapper;

	@PostMapping("/{id}")
	public void editProfile(@PathVariable Long id, @RequestBody UserProfileEditDTO userProfileEditDTO) {
		User model = userMapper.toModel(userProfileEditDTO);
		profileManagement.update(id, model);
	}

	@PostMapping("/login")
	public void login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
		profileManagement.login(userLoginDTO.username(), userLoginDTO.password());
	}

	@GetMapping("/login/{token}")
	public UserTokenResponseDTO login(@PathVariable String token) {
		UserToken login = profileManagement.login(token);
		return userTokenMapper.toResponse(login);
	}

	@PostMapping("/{userId}/{token}")
	public void setToken(@PathVariable Long userId, @PathVariable String token) {
		profileManagement.setToken(userId, token);
	}

	@GetMapping("/{username}")
	public UserLoginResponseDTO getUser(@PathVariable String username) {
		User user = profileManagement.getByUsername(username);
		return userMapper.toLoginResponse(user);
	}

	@GetMapping("/{userId}")
	public UserLoginResponseDTO getUser(@PathVariable Long userId) {
		User user = profileManagement.get(userId);
		return userMapper.toLoginResponse(user);
	}

}
