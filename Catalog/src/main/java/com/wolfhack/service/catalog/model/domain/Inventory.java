package com.wolfhack.service.catalog.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.InventoryEntity}
 */
@Data
public class Inventory implements Serializable, DomainModel {

	private Long id;

	private Integer quantity;

	private Product product;

}