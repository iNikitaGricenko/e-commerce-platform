package com.wolfhack.service.auth.client;

import com.wolfhack.service.auth.exception.UnauthorizedException;
import com.wolfhack.service.auth.model.User;
import com.wolfhack.service.auth.model.dto.UserLogin;
import com.wolfhack.service.auth.model.dto.UserRegistration;
import com.wolfhack.service.auth.model.dto.UserTokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserManagement implements UserManagementClient {

	public static final String HOST = "user";

	private final WebClient.Builder webClient;

	@Override
	public Mono<User> findByUsername(String username) {
		return webClient.build()
			.get()
			.uri(
				uriBuilder -> uriBuilder
					.host(HOST)
					.path("/profile")
					.queryParam("username", username)
					.build()
			)
			.retrieve()
			.onStatus(
				HttpStatus.NOT_FOUND::equals,
				response -> Mono.empty()
			)
			.bodyToMono(User.class)
			.onErrorStop();
	}

	@Override
	public Mono<Long> register(Mono<UserRegistration> userMono) {
		return webClient.build()
			.post()
			.uri(
				uriBuilder -> uriBuilder
					.host(HOST)
					.path("/register")
					.build()
			)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(userMono))
			.retrieve()
			.bodyToMono(Long.class);
	}

	@Override
	public Mono<Boolean> login(UserLogin userLogin) {
		return webClient.build()
			.post()
			.uri(
				uriBuilder -> uriBuilder
					.host(HOST)
					.path("/profile/login")
					.build()
			)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(userLogin))
			.retrieve()
			.onStatus(
				HttpStatus.FORBIDDEN::equals,
				response -> Mono.error(new UnauthorizedException("Token is not valid"))
			)
			.bodyToMono(boolean.class);
	}

	@Override
	public Mono<UserTokenResponseDTO> findByToken(String token) {
		return webClient.build()
			.get()
			.uri(
				uriBuilder -> uriBuilder
					.host(HOST)
					.path("/profile/login/"+token)
					.build()
			)
			.retrieve()
			.onStatus(
				HttpStatus.NOT_FOUND::equals,
				response -> Mono.error(new UnauthorizedException("Token is not valid"))
			)
			.bodyToMono(UserTokenResponseDTO.class);
	}

	@Override
	public Mono<User> findById(Long id) {
		return webClient.build()
			.get()
			.uri(
				uriBuilder -> uriBuilder
					.host(HOST)
					.path("/profile/"+id)
					.build()
			)
			.retrieve()
			.bodyToMono(User.class)
			.onErrorStop();
	}
}
