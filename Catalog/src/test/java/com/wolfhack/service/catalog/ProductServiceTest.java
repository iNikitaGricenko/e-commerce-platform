package com.wolfhack.service.catalog.service;

import com.wolfhack.service.catalog.adapter.client.OrderClient;
import com.wolfhack.service.catalog.adapter.database.*;
import com.wolfhack.service.catalog.mapper.ProductMapper;
import com.wolfhack.service.catalog.model.domain.Category;
import com.wolfhack.service.catalog.model.domain.Product;
import com.wolfhack.service.catalog.model.domain.Stock;
import com.wolfhack.service.catalog.model.dto.ProductRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductMapper productMapper;

	@Mock
	private ProductDatabaseAdapter productDatabaseAdapter;

	@Mock
	private CategoryDatabaseAdapter categoryDatabaseAdapter;

	@Mock
	private TagDatabaseAdapter tagDatabaseAdapter;

	@Mock
	private StockDatabaseAdapter stockDatabaseAdapter;

	@Mock
	private ImageDatabaseAdapter imageDatabaseAdapter;

	@Mock
	private OrderClient orderClient;

	@Mock
	private KafkaTemplate<String, String> kafkaTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createProductTest() {
		ProductRequestDTO dto = new ProductRequestDTO("Test name", "", new BigDecimal("99.9"), 1L);
		Product product = new Product();
		when(productMapper.toModel(dto)).thenReturn(product);
		when(productDatabaseAdapter.save(product)).thenReturn(1L);

		Long savedProductId = productService.create(dto);

		assertEquals(1L, savedProductId);
		verify(kafkaTemplate).send("inventory-updates", "Product saved: " + savedProductId);
	}

	@Test
	void deleteProductTest() {
		when(orderClient.isProductInOrder(1L)).thenReturn(false);

		productService.delete(1L);

		verify(productDatabaseAdapter).delete(1L);
		verify(kafkaTemplate).send("inventory-updates", "Product deleted: " + 1L);
	}

	@Test
	void addCategoryToProductTest() {
		Product product = new Product();
		Category category = new Category();
		when(productDatabaseAdapter.getById(1L)).thenReturn(product);
		when(categoryDatabaseAdapter.getById(2L)).thenReturn(category);

		productService.addCategoryToProduct(1L, 2L);

		assertTrue(product.getCategories().contains(category));
		verify(productDatabaseAdapter).partialUpdate(1L, product);
	}

	@Test
	void updateStockTest() {
		Product product = new Product();
		Stock stock = new Stock();
		stock.setQuantity(10);
		product.setStock(stock);
		when(productDatabaseAdapter.getById(1L)).thenReturn(product);

		productService.updateStock(1L, -5);

		assertEquals(5, stock.getQuantity());
		verify(stockDatabaseAdapter).save(stock);
		verify(kafkaTemplate).send("inventory-updates", "Stock updated for product: " + 1L);
	}

}
