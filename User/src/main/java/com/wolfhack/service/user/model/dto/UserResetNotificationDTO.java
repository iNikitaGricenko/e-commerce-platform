package com.wolfhack.service.user.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.user.model.domain.User}
 */
public record UserResetNotificationDTO(
	Long id,
	String email,
	String username,
	String resetToken
) implements Serializable { }