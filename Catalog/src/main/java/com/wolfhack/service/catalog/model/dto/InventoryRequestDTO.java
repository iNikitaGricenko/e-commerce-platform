package com.wolfhack.service.catalog.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.InventoryEntity}
 */
public record InventoryRequestDTO(
	Integer quantity,
	String location,
	Long productId
) implements Serializable {}