package com.wolfhack.service.review.adapter.client;

import com.wolfhack.service.review.model.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "catalog")
public interface CatalogClient {

	@RequestMapping(value = "/api/catalog/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ProductDTO getProduct(@PathVariable("id") Long id);

}
