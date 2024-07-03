package com.wolfhack.service.catalog.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.InventoryEntity}
 */
public record InventoryResponseDTO(Long id, Integer quantity,
                                   ProductResponseDTO product) implements Serializable {
}