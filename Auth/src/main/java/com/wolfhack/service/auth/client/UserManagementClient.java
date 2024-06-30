package com.wolfhack.service.auth.client;

import com.wolfhack.service.auth.model.User;
import com.wolfhack.service.auth.model.dto.UserLogin;
import com.wolfhack.service.auth.model.dto.UserRegistration;
import com.wolfhack.service.auth.model.dto.UserTokenResponseDTO;
import reactor.core.publisher.Mono;

public interface UserManagementClient {

	Mono<User> findByUsername(String subject);

	Mono<Long> register(Mono<UserRegistration> map);

	Mono<Boolean> login(UserLogin userLogin);

	Mono<UserTokenResponseDTO> findByToken(String token);

	Mono<User> findById(Long id);
}
