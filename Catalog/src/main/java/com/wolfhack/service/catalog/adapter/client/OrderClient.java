package com.wolfhack.service.catalog.adapter.client;

import com.wolfhack.common.model.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order")
public interface OrderClient {

	@GetMapping("/api/orders/{id}")
	OrderDTO getOrderById(@PathVariable("id") Long id);

	@GetMapping("/api/orders/product/{productId}")
	boolean isProductInOrder(@PathVariable("productId") Long productId);

}
