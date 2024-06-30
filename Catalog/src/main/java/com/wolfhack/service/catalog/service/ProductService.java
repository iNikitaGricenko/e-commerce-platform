package com.wolfhack.service.catalog.service;

import com.wolfhack.service.catalog.adapter.database.ProductDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.ProductMapper;
import com.wolfhack.service.catalog.model.domain.Product;
import com.wolfhack.service.catalog.model.dto.ProductRequestDTO;
import com.wolfhack.service.catalog.model.dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductMapper productMapper;

	private final ProductDatabaseAdapter productDatabaseAdapter;

	public Long create(ProductRequestDTO dto) {
		Product product = productMapper.toModel(dto);

		return productDatabaseAdapter.save(product);
	}

	public Long partialUpdate(Long id, ProductRequestDTO dto) {
		Product product = productMapper.toModel(dto);

		return productDatabaseAdapter.partialUpdate(id, product);
	}

	public Long update(Long id, ProductRequestDTO dto) {
		Product product = productMapper.toModel(dto);

		return productDatabaseAdapter.update(id, product);
	}

	public ProductResponseDTO getById(Long id) {
		return productMapper.toResponse(
			productDatabaseAdapter.getById(id)
		);
	}

	public List<ProductResponseDTO> getByCategoryId(Long categoryId) {
		return productDatabaseAdapter.getByCategory(categoryId)
			.stream()
			.map(productMapper::toResponse)
			.toList();
	}

	public void delete(Long id) {
		productDatabaseAdapter.delete(id);
	}

}
