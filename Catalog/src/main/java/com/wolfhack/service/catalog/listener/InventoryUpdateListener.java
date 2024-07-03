package com.wolfhack.service.catalog.listener;

import com.wolfhack.service.catalog.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryUpdateListener {

	private final InventoryService inventoryService;

	@KafkaListener(topics = "inventory-updates", groupId = "catalog-group")
	public void listen(String message) {
		// TODO: Process inventory update message
	}

}
