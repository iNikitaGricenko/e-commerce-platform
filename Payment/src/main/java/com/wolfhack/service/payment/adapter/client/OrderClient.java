package com.wolfhack.service.payment.adapter.client;

import com.wolfhack.common.model.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order")
public interface OrderClient {

	@GetMapping("/orders/{id}")
	OrderDTO getOrderById(@PathVariable("id") Long id);

}
