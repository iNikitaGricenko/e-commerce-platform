package com.wolfhack.service.catalog.mapper;

import com.wolfhack.service.catalog.model.domain.Image;
import com.wolfhack.service.catalog.model.dto.ImageRequestDTO;
import com.wolfhack.service.catalog.model.entity.ImageEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface ImageMapper {

	ImageEntity toEntity(Image image);

	Image toDModel(ImageEntity imageEntity);

	Image toModel(ImageRequestDTO imageRequestDTO);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ImageEntity partialUpdate(Image image, @MappingTarget ImageEntity imageEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	ImageEntity update(Image image, @MappingTarget ImageEntity imageEntity);

}