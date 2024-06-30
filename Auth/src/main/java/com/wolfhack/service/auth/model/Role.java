package com.wolfhack.service.auth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	ADMIN("Admin", "ROLE_ADMIN"),
	CUSTOMER("User", "ROLE_USER");

	private final String simpleName;
	private final String roleName;

}
