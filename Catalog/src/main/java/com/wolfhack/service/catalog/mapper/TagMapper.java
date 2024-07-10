package com.wolfhack.service.catalog.mapper;

import com.wolfhack.service.catalog.model.domain.Tag;
import com.wolfhack.service.catalog.model.dto.TagRequestDTO;
import com.wolfhack.service.catalog.model.dto.TagResponseDTO;
import com.wolfhack.service.catalog.model.entity.TagEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface TagMapper {

	TagEntity toEntity(Tag tag);

	Tag toModel(TagEntity tagEntity);

	TagResponseDTO toResponse(Tag tag);

	Tag toModel(TagRequestDTO tagRequestDTO);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	TagEntity partialUpdate(Tag tag, @MappingTarget TagEntity tagEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	TagEntity update(Tag tag, @MappingTarget TagEntity tagEntity);

}