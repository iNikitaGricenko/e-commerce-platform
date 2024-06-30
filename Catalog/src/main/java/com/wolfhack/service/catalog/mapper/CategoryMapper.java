package com.wolfhack.service.catalog.mapper;

import com.wolfhack.service.catalog.model.domain.Category;
import com.wolfhack.service.catalog.model.dto.CategoryRequestDTO;
import com.wolfhack.service.catalog.model.dto.CategoryResponseDTO;
import com.wolfhack.service.catalog.model.entity.CategoryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

	CategoryEntity toEntity(Category orderItem);

	Category toModel(CategoryEntity orderItemEntity);

	Category toModel(CategoryRequestDTO orderItem);

	CategoryResponseDTO toResponse(Category category);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	CategoryEntity partialUpdate(Category from, @MappingTarget CategoryEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	CategoryEntity update(Category from, @MappingTarget CategoryEntity to);

}