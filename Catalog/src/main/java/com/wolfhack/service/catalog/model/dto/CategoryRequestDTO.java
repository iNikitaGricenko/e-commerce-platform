package com.wolfhack.service.catalog.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.domain.Category}
 */
public record CategoryRequestDTO(
	@NotBlank
	String name
) implements Serializable { }