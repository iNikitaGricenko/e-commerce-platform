package com.wolfhack.service.catalog.model.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.BrandEntity}
 */
public record BrandResponseDTO(Long id, String name,
                               Set<ProductResponseDTO> products) implements Serializable {
}