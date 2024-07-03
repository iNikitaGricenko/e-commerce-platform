package com.wolfhack.service.catalog.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import com.wolfhack.service.catalog.model.entity.ReviewEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link ReviewEntity}
 */
@Data
public class Review implements Serializable, DomainModel {

	private Long id;

	private Product product;

}