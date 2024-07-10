package com.wolfhack.service.catalog.mapper;

import com.wolfhack.service.catalog.model.domain.Inventory;
import com.wolfhack.service.catalog.model.dto.InventoryRequestDTO;
import com.wolfhack.service.catalog.model.dto.InventoryResponseDTO;
import com.wolfhack.service.catalog.model.entity.InventoryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface InventoryMapper {

	InventoryEntity toEntity(Inventory inventory);

	Inventory toModel(InventoryEntity inventoryEntity);

	@Mapping(source = "productId", target = "product.id")
	Inventory toModel(InventoryRequestDTO inventoryRequestDTO);

	InventoryResponseDTO toResponse(Inventory inventory);

	@Named(value = "partialUpdate")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	InventoryEntity partialUpdate(Inventory inventory, @MappingTarget InventoryEntity inventoryEntity);

	@Named(value = "update")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	InventoryEntity update(Inventory inventory, @MappingTarget InventoryEntity inventoryEntity);


}