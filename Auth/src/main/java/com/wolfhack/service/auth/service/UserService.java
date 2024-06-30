package com.wolfhack.service.auth.service;

import com.wolfhack.service.auth.client.UserManagementClient;
import com.wolfhack.service.auth.model.User;
import com.wolfhack.service.auth.model.dto.UserRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserManagementClient userManagementClient;

	public Mono<Long> save(Mono<UserRegistration> user) {
		return userManagementClient.register(user);
	}

	public Mono<User> get(String username) {
		return userManagementClient.findByUsername(username);
	}

}
