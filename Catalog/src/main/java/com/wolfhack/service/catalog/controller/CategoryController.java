package com.wolfhack.service.catalog.controller;

import com.wolfhack.service.catalog.model.dto.CategoryRequestDTO;
import com.wolfhack.service.catalog.model.dto.CategoryResponseDTO;
import com.wolfhack.service.catalog.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponseDTO getById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CategoryResponseDTO> getAll() {
		return categoryService.getAllCategories();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody CategoryRequestDTO category) {
		return categoryService.createCategory(category);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long updateFull(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO category) {
		return categoryService.updateCategory(id, category);
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long updatePartial(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO category) {
		return categoryService.partialUpdateCategory(id, category);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		categoryService.deleteCategory(id);
	}

}
