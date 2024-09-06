package com.wolfhack.service.user.mapper;

import com.wolfhack.common.model.dto.UserDTO;
import com.wolfhack.service.user.model.domain.User;
import com.wolfhack.service.user.model.dto.UserLoginDTO;
import com.wolfhack.service.user.model.dto.UserLoginResponseDTO;
import com.wolfhack.service.user.model.dto.UserProfileEditDTO;
import com.wolfhack.service.user.model.dto.UserRegisterDTO;
import com.wolfhack.service.user.model.entity.UserEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

	UserEntity toEntity(User user);

	User toModel(UserEntity userEntity);

	User toModel(UserRegisterDTO userRegisterDto);

	User toModel(UserProfileEditDTO userProfileEditDTO);

	User toModel(UserLoginDTO userLoginDTO);

	UserDTO toDTO(UserEntity userEntity);

	UserLoginResponseDTO toLoginResponse(User user);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserEntity partialUpdate(UserLoginResponseDTO userLoginResponseDTO, @MappingTarget UserEntity userEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserEntity partialUpdate(User from, @MappingTarget UserEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	UserEntity update(User from, @MappingTarget UserEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	User partialUpdate(User from, @MappingTarget User to);

}