package com.wolfhack.service.user.controller;

import com.wolfhack.common.model.dto.UserDTO;
import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public DomainPage<UserDTO> getPage(Pageable pageable) {
		return userService.getPage(pageable);
	}

	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<UserDTO> getAll() {
		return userService.getAll();
	}

}
