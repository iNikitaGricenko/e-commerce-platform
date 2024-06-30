package com.wolfhack.service.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	ADMIN("ROLE_ADMIN"), CUSTOMER("ROLE_USER");

	private final String role;

}
