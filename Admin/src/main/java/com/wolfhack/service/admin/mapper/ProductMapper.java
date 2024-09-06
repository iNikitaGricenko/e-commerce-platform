package com.wolfhack.service.admin.mapper;

import com.wolfhack.common.model.dto.ProductDTO;
import com.wolfhack.service.admin.model.domain.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

	Product toModel(ProductDTO dto);

	ProductDTO toDTO(Product model);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Product partialUpdate(@MappingTarget Product model, ProductDTO modelDTO);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	Product update(@MappingTarget Product model, ProductDTO modelDTO);

}