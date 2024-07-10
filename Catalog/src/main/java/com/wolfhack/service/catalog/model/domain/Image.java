package com.wolfhack.service.catalog.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.ImageEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable, DomainModel {

	private Long id;

	private String url;

	private Product product;

}