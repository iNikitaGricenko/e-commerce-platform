package com.wolfhack.service.order.mapper;

import com.wolfhack.service.order.model.domain.Order;
import com.wolfhack.service.order.model.domain.OrderItem;
import com.wolfhack.service.order.model.dto.OrderRequestDTO;
import com.wolfhack.service.order.model.dto.OrderResponseDTO;
import com.wolfhack.service.order.model.entity.OrderEntity;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

	OrderEntity toEntity(Order order);

	Order toModel(OrderEntity orderEntity);

	Order toModel(OrderRequestDTO dto);

	@Mapping(target = "totalAmount", source = "items", qualifiedByName = "itemsToTotalAmount")
	@Mapping(target = "totalPrice", source = "items", qualifiedByName = "itemsToTotalPrice")
	OrderResponseDTO toResponse(Order model);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	OrderEntity partialUpdate(Order from, @MappingTarget OrderEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	OrderEntity update(Order from, @MappingTarget OrderEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Order partialUpdate(OrderRequestDTO from, @MappingTarget Order to);

	@Named("itemsToTotalAmount")
	default Integer getTotalAmount(List<OrderItem> items) {
		return items
			.stream()
			.mapToInt(OrderItem::getTotalAmount)
			.sum();
	}

	@Named("itemsToTotalPrice")
	default Double getTotalPrice(List<OrderItem> items) {
		return items
			.stream()
			.mapToDouble(
				value ->
					value
						.getUnitPrice()
						.multiply(
							BigDecimal.valueOf(
								value.getTotalAmount().longValue()
							)
						)
						.doubleValue()
			)
			.sum();
	}

}