package com.wolfhack.service.admin.model.dto;

public record LogEntryDTO(
	String timestamp,
	String level,
	String message
) { }
