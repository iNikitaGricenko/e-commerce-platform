package com.wolfhack.service.catalog.integration;

import com.wolfhack.service.catalog.CatalogApplication;
import com.wolfhack.service.catalog.model.dto.ProductRequestDTO;
import com.wolfhack.service.catalog.model.dto.ProductResponseDTO;
import com.wolfhack.service.catalog.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = CatalogApplication.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = true, topics = {"inventory-updates", "order-created"})
@Testcontainers
@TestPropertySource(properties = {
	"spring.datasource.url=jdbc:tc:postgresql:13:///catalog",
	"spring.kafka.bootstrap-servers=${kafka.bootstrap-servers}"
})
class ProductServiceIntegrationTest {

	@Container
	public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13");

	@Container
	public static KafkaContainer kafkaContainer = new KafkaContainer();

	@Autowired
	private ProductService productService;

	@Test
	void createProductIntegrationTest() {
		ProductRequestDTO dto = new ProductRequestDTO("Test Product", "Test description", new BigDecimal("19.99"), 1L);

		Long productId = productService.create(dto);

		assertNotNull(productId);
		ProductResponseDTO product = productService.getById(productId);
		assertEquals("Test Product", product.name());
	}

}
