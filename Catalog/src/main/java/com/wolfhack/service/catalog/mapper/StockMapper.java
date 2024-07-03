package com.wolfhack.service.catalog.mapper;

import com.wolfhack.service.catalog.model.domain.Stock;
import com.wolfhack.service.catalog.model.entity.ProductEntity;
import com.wolfhack.service.catalog.model.entity.StockEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface StockMapper {

	StockEntity toEntity(Stock stock);

	Stock toModel(StockEntity stockEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	StockEntity partialUpdate(Stock stock, @MappingTarget StockEntity stockEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	StockEntity update(Stock stock, @MappingTarget StockEntity stockEntity);

	@AfterMapping
	default void linkProduct(@MappingTarget StockEntity stockEntity) {
		ProductEntity product = stockEntity.getProduct();
		if (product != null) {
			product.setStock(stockEntity);
		}
	}

}