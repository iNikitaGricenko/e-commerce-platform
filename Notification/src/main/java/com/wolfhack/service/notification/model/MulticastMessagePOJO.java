package com.wolfhack.service.notification.model;

import java.io.Serializable;
import java.util.List;

public record MulticastMessagePOJO(
	String message,
	List<String> registrationTokens
) implements Serializable { }
