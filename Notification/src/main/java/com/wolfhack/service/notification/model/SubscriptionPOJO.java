package com.wolfhack.service.notification.model;

import java.io.Serializable;

public record SubscriptionPOJO(
	String topic,
	String registrationToken
) implements Serializable { }
