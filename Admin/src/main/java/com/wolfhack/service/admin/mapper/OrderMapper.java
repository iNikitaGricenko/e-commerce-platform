package com.wolfhack.service.admin.mapper;

import com.wolfhack.common.model.dto.OrderDTO;
import com.wolfhack.service.admin.model.domain.Order;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

	Order toModel(OrderDTO dto);

	OrderDTO toDTO(Order order);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Order partialUpdate(@MappingTarget Order order, OrderDTO orderDTO);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	Order update(@MappingTarget Order order, OrderDTO orderDTO);

}