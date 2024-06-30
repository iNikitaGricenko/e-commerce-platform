package com.wolfhack.service.admin.adapter.client;

import com.wolfhack.common.model.dto.OrderDTO;
import com.wolfhack.service.admin.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order", configuration = FeignConfig.class)
public interface OrderClient {

	@GetMapping("/orders/{id}")
	OrderDTO getOrderById(@PathVariable("id") Long id);

}
