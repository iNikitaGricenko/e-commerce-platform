package com.wolfhack.service.catalog.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.TagEntity}
 */
public record TagResponseDTO(Long id, String name) implements Serializable {
}