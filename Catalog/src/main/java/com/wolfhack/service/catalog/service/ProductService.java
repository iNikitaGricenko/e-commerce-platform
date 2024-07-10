package com.wolfhack.service.catalog.service;

import com.wolfhack.service.catalog.adapter.client.OrderClient;
import com.wolfhack.service.catalog.adapter.database.*;
import com.wolfhack.service.catalog.mapper.ProductMapper;
import com.wolfhack.service.catalog.model.domain.*;
import com.wolfhack.service.catalog.model.dto.ProductRequestDTO;
import com.wolfhack.service.catalog.model.dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductMapper productMapper;

	private final ProductDatabaseAdapter productDatabaseAdapter;

	private final CategoryDatabaseAdapter categoryDatabaseAdapter;

	private final TagDatabaseAdapter tagDatabaseAdapter;

	private final StockDatabaseAdapter stockDatabaseAdapter;

	private final ImageDatabaseAdapter imageDatabaseAdapter;

	private final OrderClient orderClient;

	private final KafkaTemplate<String, String> kafkaTemplate;

	public Long create(ProductRequestDTO dto) {
		Product product = productMapper.toModel(dto);

		Long savedProductId = productDatabaseAdapter.save(product);

		kafkaTemplate.send("inventory-updates", "Product saved: " + savedProductId);

		return savedProductId;
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
		boolean isInOrder = orderClient.isProductInOrder(id);

		if (isInOrder) {
			throw new RuntimeException("Product is part of an order and cannot be deleted.");
		}

		productDatabaseAdapter.delete(id);
		kafkaTemplate.send("inventory-updates", "Product deleted: " + id);
	}

	public void addCategoryToProduct(Long productId, Long categoryId) {
		Product product = productDatabaseAdapter.getById(productId);
		Category category = categoryDatabaseAdapter.getById(categoryId);
		if (product != null && category != null) {
			product.getCategories().add(category);
			productDatabaseAdapter.partialUpdate(productId, product);
		}
	}

	public void addTagToProduct(Long productId, Long tagId) {
		Product product = productDatabaseAdapter.getById(productId);
		Tag tag = tagDatabaseAdapter.getById(tagId);
		if (product != null && tag != null) {
			product.getTags().add(tag);
			productDatabaseAdapter.save(product);
		}
	}

	public List<ProductResponseDTO> searchProductsByTag(String tagName) {
		return productDatabaseAdapter.findByTags(tagName)
			.stream()
			.map(productMapper::toResponse)
			.toList();
	}

	public void updateStock(Long productId, int quantityChange) {
		Product product = productDatabaseAdapter.getById(productId);
		if (product != null) {
			Stock stock = product.getStock();
			if (stock != null) {
				int newQuantity = stock.getQuantity() + quantityChange;
				stock.setQuantity(newQuantity);
				stockDatabaseAdapter.save(stock);
				kafkaTemplate.send("inventory-updates", "Stock updated for product: " + productId);
			}
		}
	}

	public void uploadImageToProduct(Long productId, String imageUrl) {
		Product product = productDatabaseAdapter.getById(productId);
		if (product != null) {
			Image image = new Image();
			image.setUrl(imageUrl);
			image.setProduct(product);
			imageDatabaseAdapter.save(image);
		}
	}

}
