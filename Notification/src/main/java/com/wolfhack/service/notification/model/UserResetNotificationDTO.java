package com.wolfhack.service.notification.model;

import java.io.Serializable;

public record UserResetNotificationDTO(
	Long id,
	String email,
	String username,
	String resetToken
) implements Serializable { }