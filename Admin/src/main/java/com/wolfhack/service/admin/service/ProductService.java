package com.wolfhack.service.admin.service;

import com.wolfhack.service.admin.adapter.client.CatalogClient;
import com.wolfhack.service.admin.mapper.ProductMapper;
import com.wolfhack.service.admin.model.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final CatalogClient catalogClient;

	private final ProductMapper productMapper;

	public void save(Product product) {
		catalogClient.saveProduct(
			productMapper.toDTO(product)
		);
	}

	public List<Product> findAll() {
		return catalogClient.getAllProducts()
			.stream()
			.map(productMapper::toModel)
			.toList();
	}

	public Product findByName(String name) {
		return productMapper.toModel(
			catalogClient.getProduct(name)
		);
	}

	public void delete(Product product) {
		catalogClient.deleteProduct(product.getId());
	}

}
