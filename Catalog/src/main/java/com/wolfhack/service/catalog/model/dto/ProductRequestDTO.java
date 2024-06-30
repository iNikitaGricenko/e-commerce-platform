package com.wolfhack.service.catalog.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.domain.Product}
 */
public record ProductRequestDTO(
	@NotBlank
	String name,

	String description,

	@NotNull
	@DecimalMin("0.99")
	BigDecimal price,

	@NotNull
	@Min(0)
	Long categoryId
) implements Serializable { }