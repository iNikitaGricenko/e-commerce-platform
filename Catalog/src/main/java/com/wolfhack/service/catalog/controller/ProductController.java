package com.wolfhack.service.catalog.controller;

import com.wolfhack.service.catalog.model.dto.ProductRequestDTO;
import com.wolfhack.service.catalog.model.dto.ProductResponseDTO;
import com.wolfhack.service.catalog.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody ProductRequestDTO product) {
        return productService.create(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getByCategory(@PathVariable Long categoryId) {
        return productService.getByCategoryId(categoryId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateFull(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO product) {
        return productService.update(id, product);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updatePartial(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO product) {
        return productService.partialUpdate(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PostMapping("/{productId}/categories/{categoryId}")
    public void addCategory(@PathVariable Long productId, @PathVariable Long categoryId) {
        productService.addCategoryToProduct(productId, categoryId);
    }

    @PostMapping("/{productId}/tags/{tagId}")
    public void addTag(@PathVariable Long productId, @PathVariable Long tagId) {
        productService.addTagToProduct(productId, tagId);
    }

    @GetMapping("/search")
    public List<ProductResponseDTO> searchByTag(@RequestParam String tagName) {
        return productService.searchProductsByTag(tagName);
    }

    @PutMapping("/{productId}/stock")
    public void updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        productService.updateStock(productId, quantity);
    }

    @PostMapping("/{productId}/images")
    public void uploadImageToProduct(@PathVariable Long productId, @RequestParam String imageUrl) {
        productService.uploadImageToProduct(productId, imageUrl);
    }

}
