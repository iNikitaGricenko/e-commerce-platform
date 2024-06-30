package com.wolfhack.service.auth.mapper;

import com.wolfhack.service.auth.model.User;
import com.wolfhack.service.auth.model.dto.UserRegistration;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

	User toUser(UserRegistration userRegistration);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	User partialUpdate(User user, @MappingTarget User userEntity);
}