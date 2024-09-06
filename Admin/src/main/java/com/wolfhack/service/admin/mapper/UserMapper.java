package com.wolfhack.service.admin.mapper;

import com.wolfhack.common.model.dto.UserDTO;
import com.wolfhack.service.admin.model.domain.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

	User toModel(UserDTO dto);

	UserDTO toDTO(User model);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	User partialUpdate(@MappingTarget User model, UserDTO modelDTO);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	User update(@MappingTarget User model, UserDTO modelDTO);

}