package com.wolfhack.service.catalog.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Product implements DomainModel, Serializable {

	private Long id;

	private String name;

	private String description;

	private BigDecimal price;

	private Category category;

}
