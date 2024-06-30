package com.wolfhack.service.notification.model;

public record MailMessagePOJO(
	String to,
	String subject,
	String body
) { }
