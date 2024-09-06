package com.wolfhack.service.admin.model.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

	private Long id;

	private String name;

	private String description;

	private BigDecimal price;

	private Long categoryId;

}
