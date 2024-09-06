package com.wolfhack.service.admin.mapper;

import com.wolfhack.common.model.dto.ReviewDTO;
import com.wolfhack.service.admin.model.domain.Review;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {

	Review toModel(ReviewDTO dto);

	ReviewDTO toDTO(Review model);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Review partialUpdate(@MappingTarget Review model, ReviewDTO modelDTO);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	Review update(@MappingTarget Review model, ReviewDTO modelDTO);

}