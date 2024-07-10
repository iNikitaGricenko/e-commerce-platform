package com.wolfhack.service.catalog.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
public class Product implements DomainModel, Serializable {

	private Long id;

	private String name;

	private String description;

	private BigDecimal price;

	private String sku;

	private Brand brand;

	private Stock stock;

	private List<Inventory> inventories;

	private Set<Category> categories;

	private Set<Tag> tags;

	private Set<Image> images;

}
