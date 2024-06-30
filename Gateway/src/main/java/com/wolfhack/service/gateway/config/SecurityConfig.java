package com.wolfhack.service.gateway.config;

import com.wolfhack.service.gateway.service.AuthenticationManager;
import com.wolfhack.service.gateway.service.SecurityContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationManager authenticationManager;
	private final SecurityContextRepository securityContextRepository;

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
			.exceptionHandling(spec ->
				spec
					.authenticationEntryPoint(getAuthenticationEntryPoint())
					.accessDeniedHandler(getAccessDeniedHandler())
			)
			.csrf(ServerHttpSecurity.CsrfSpec::disable)
			.httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
			.formLogin(ServerHttpSecurity.FormLoginSpec::disable)
			.authenticationManager(authenticationManager)
			.securityContextRepository(securityContextRepository)
			.authorizeExchange(
				spec -> spec
					.pathMatchers("/authorization/login").permitAll()
					.pathMatchers("/authorization/jwt/validate").permitAll()
					.pathMatchers("/authorization/register").permitAll()
					.anyExchange().authenticated()
			)
			.build();
	}

	private ServerAccessDeniedHandler getAccessDeniedHandler() {
		return (exchange, e) -> Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN));
	}

	private ServerAuthenticationEntryPoint getAuthenticationEntryPoint() {
		return (exchange, ex) -> Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
	}

}
