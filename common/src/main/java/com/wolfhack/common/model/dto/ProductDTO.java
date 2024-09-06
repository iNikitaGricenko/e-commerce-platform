package com.wolfhack.common.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProductDTO(
	Long id,
	String name,
	String description,
	BigDecimal price,
	Long categoryId
) implements Serializable {}