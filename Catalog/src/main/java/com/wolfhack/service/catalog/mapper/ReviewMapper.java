package com.wolfhack.service.catalog.mapper;

import com.wolfhack.service.catalog.model.domain.Review;
import com.wolfhack.service.catalog.model.entity.ReviewEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface ReviewMapper {

	ReviewEntity toEntity(Review review);

	Review toDto(ReviewEntity reviewEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ReviewEntity partialUpdate(Review review, @MappingTarget ReviewEntity reviewEntity);

}