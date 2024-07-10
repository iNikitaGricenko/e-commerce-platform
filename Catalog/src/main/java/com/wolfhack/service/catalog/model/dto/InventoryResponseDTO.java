package com.wolfhack.service.catalog.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.InventoryEntity}
 */
public record InventoryResponseDTO(
	Long id,
	Integer quantity,
	String location,
	LocalDateTime lastUpdated,
	ProductResponseDTO product
) implements Serializable {}