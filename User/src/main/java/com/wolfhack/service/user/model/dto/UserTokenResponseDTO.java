package com.wolfhack.service.user.model.dto;

import java.io.Serializable;

public record UserTokenResponseDTO(
	Long id,
	String username
) implements Serializable { }
