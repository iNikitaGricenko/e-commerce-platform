package com.wolfhack.service.review.mapper;

import com.wolfhack.service.review.model.domain.Review;
import com.wolfhack.service.review.model.dto.ReviewRequestDTO;
import com.wolfhack.service.review.model.dto.ReviewResponseDTO;
import com.wolfhack.service.review.model.entity.ReviewEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {

	ReviewEntity toEntity(Review review);

	Review toModel(ReviewEntity reviewEntity);

	Review toModel(ReviewRequestDTO dto);

	ReviewResponseDTO toResponse(Review review);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ReviewEntity partialUpdate(Review review, @MappingTarget ReviewEntity reviewEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	ReviewEntity update(Review review, @MappingTarget ReviewEntity reviewEntity);

}