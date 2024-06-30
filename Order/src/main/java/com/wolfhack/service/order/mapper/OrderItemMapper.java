package com.wolfhack.service.order.mapper;

import com.wolfhack.service.order.model.domain.OrderItem;
import com.wolfhack.service.order.model.dto.OrderItemRequestDTO;
import com.wolfhack.service.order.model.entity.OrderItemEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {

	OrderItemEntity toEntity(OrderItem orderItem);

	OrderItem toModel(OrderItemEntity orderItemEntity);

	OrderItem toModel(OrderItemRequestDTO orderItem);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	OrderItemEntity partialUpdate(OrderItem from, @MappingTarget OrderItemEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	OrderItemEntity update(OrderItem from, @MappingTarget OrderItemEntity to);

}