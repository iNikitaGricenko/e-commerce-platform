package com.wolfhack.service.admin.adapter.client;

import com.wolfhack.common.model.dto.OrderDTO;
import com.wolfhack.common.wrapper.DomainPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "order")
public interface OrderClient {

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/api/orders",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	DomainPage<OrderDTO> getAllOrders();

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/api/orders/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	OrderDTO getOrder(@PathVariable("id") Long id);

	@RequestMapping(
		method = RequestMethod.DELETE,
		value = "/api/orders/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	void cancelOrder(@PathVariable("id") Long id);

}
