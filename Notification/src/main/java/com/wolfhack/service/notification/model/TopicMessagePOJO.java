package com.wolfhack.service.notification.model;

import java.io.Serializable;

public record TopicMessagePOJO(
	String message,
	String topic
) implements Serializable { }
