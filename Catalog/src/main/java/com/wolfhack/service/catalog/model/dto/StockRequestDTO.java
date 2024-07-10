package com.wolfhack.service.catalog.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.StockEntity}
 */
public record StockRequestDTO(int quantity, Long productId) implements Serializable {
}