package com.wolfhack.service.catalog.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.domain.Product}
 */
public record ProductResponseDTO(
	Long id,
	String name,
	String description,
	BigDecimal price,
	Long categoryId
) implements Serializable { }