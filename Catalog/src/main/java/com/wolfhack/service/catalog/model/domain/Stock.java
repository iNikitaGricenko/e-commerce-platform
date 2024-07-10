package com.wolfhack.service.catalog.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import com.wolfhack.service.catalog.model.entity.StockEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link StockEntity}
 */
@Data
public class Stock implements Serializable, DomainModel {

	private Long id;

	private int quantity;

	private Product product;

}