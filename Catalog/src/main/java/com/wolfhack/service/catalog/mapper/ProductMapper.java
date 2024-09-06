package com.wolfhack.service.catalog.mapper;

import com.wolfhack.service.catalog.model.domain.Product;
import com.wolfhack.service.catalog.model.dto.ProductRequestDTO;
import com.wolfhack.service.catalog.model.dto.ProductResponseDTO;
import com.wolfhack.service.catalog.model.entity.ProductEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

	ProductEntity toEntity(Product order);

	Product toModel(ProductEntity orderEntity);

	Product toModel(ProductRequestDTO dto);

	ProductResponseDTO toResponse(Product model);

	@Named(value = "partialUpdate")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ProductEntity partialUpdate(Product from, @MappingTarget ProductEntity to);

	@Named(value = "update")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	ProductEntity update(Product from, @MappingTarget ProductEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Product partialUpdate(ProductRequestDTO from, @MappingTarget Product to);

	@AfterMapping
	default void linkInventories(@MappingTarget ProductEntity productEntity) {
		productEntity.getInventories().forEach(inventory -> inventory.setProduct(productEntity));
	}

	@AfterMapping
	default void linkImages(@MappingTarget ProductEntity productEntity) {
		productEntity.getImages().forEach(image -> image.setProduct(productEntity));
	}

}