package com.wolfhack.service.admin.adapter.client;

import com.wolfhack.common.model.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "catalog")
public interface CatalogClient {

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/api/catalog/products",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	List<ProductDTO> getAllProducts();

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/api/catalog/products/{name}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ProductDTO getProduct(@PathVariable("name") String name);

	@RequestMapping(
		method = RequestMethod.POST,
		value = "/api/catalog/products",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	Long saveProduct(ProductDTO product);

	@RequestMapping(
		method = RequestMethod.PATCH,
		value = "/api/catalog/products/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	Long partialUpdateProduct(@PathVariable("id") Long id, ProductDTO product);

	@RequestMapping(
		method = RequestMethod.DELETE,
		value = "/api/catalog/products/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	void deleteProduct(@PathVariable("id") Long id);

}
