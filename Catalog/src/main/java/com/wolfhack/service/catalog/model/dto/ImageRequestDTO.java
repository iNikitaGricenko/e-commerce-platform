package com.wolfhack.service.catalog.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.ImageEntity}
 */
public record ImageRequestDTO(String url) implements Serializable {
}