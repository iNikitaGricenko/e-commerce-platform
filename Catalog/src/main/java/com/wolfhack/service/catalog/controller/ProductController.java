package com.wolfhack.service.catalog.controller;

import com.wolfhack.service.catalog.model.dto.ProductRequestDTO;
import com.wolfhack.service.catalog.model.dto.ProductResponseDTO;
import com.wolfhack.service.catalog.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/catalog/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody ProductRequestDTO product) {
        return productService.create(product);
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

}
