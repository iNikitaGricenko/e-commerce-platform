package com.wolfhack.service.catalog.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.BrandEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable, DomainModel {

	private Long id;

	private String name;

	private Set<Product> products;

}