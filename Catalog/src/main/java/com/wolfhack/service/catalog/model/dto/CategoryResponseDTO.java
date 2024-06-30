package com.wolfhack.service.catalog.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.domain.Category}
 */
public record CategoryResponseDTO(
	Long id,
	String name,
	List<Long> productIds
) implements Serializable { }