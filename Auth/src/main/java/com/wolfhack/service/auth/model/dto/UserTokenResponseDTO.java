package com.wolfhack.service.auth.model.dto;

import java.io.Serializable;

public record UserTokenResponseDTO(
	Long id,
	String username
) implements Serializable { }
