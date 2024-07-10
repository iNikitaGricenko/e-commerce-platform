package com.wolfhack.service.catalog.listener;

import com.wolfhack.service.catalog.model.event.OrderEvent;
import com.wolfhack.service.catalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStockUpdateListener {

	private final ProductService productService;

	@KafkaListener(topics = "order-created", groupId = "catalog")
	public void consumeOrderCreated(OrderEvent orderEvent) {
		orderEvent
			.getItems()
			.forEach(item -> {
				productService.updateStock(item.productId(), -item.totalAmount());
			});
	}

}
