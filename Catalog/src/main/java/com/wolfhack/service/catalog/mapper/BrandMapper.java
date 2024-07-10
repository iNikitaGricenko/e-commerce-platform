package com.wolfhack.service.catalog.mapper;

import com.wolfhack.service.catalog.model.domain.Brand;
import com.wolfhack.service.catalog.model.dto.BrandRequestDTO;
import com.wolfhack.service.catalog.model.dto.BrandResponseDTO;
import com.wolfhack.service.catalog.model.entity.BrandEntity;
import com.wolfhack.service.catalog.model.entity.ProductEntity;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface BrandMapper {

	BrandEntity toEntity(Brand brand);

	Brand toModel(BrandEntity brandEntity);

	Brand toModel(BrandRequestDTO brandRequestDTO);

	BrandResponseDTO toResponse(Brand brand);

	@Named(value = "partialUpdate")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	BrandEntity partialUpdate(Brand brand, @MappingTarget BrandEntity brandEntity);

	@Named(value = "update")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	BrandEntity update(Brand brand, @MappingTarget BrandEntity brandEntity);

	@AfterMapping
	default void linkProducts(@MappingTarget BrandEntity brandEntity) {
		brandEntity.getProducts().forEach(product -> product.setBrand(brandEntity));
	}

	default Set<Long> productsToProductIds(Set<ProductEntity> products) {
		return products.stream().map(ProductEntity::getId).collect(Collectors.toSet());
	}

}