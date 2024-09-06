package com.wolfhack.service.catalog.controller;

import com.wolfhack.service.catalog.model.dto.BrandRequestDTO;
import com.wolfhack.service.catalog.model.dto.BrandResponseDTO;
import com.wolfhack.service.catalog.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/brands")
@RequiredArgsConstructor
public class BrandController {

	private final BrandService brandService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<BrandResponseDTO> getAll() {
		return brandService.getAllBrands();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public BrandResponseDTO getById(@PathVariable Long id) {
		return brandService.getBrandById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@Valid @RequestBody BrandRequestDTO brand) {
		return brandService.createBrand(brand);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long update(@PathVariable Long id, @Valid @RequestBody BrandRequestDTO brand) {
		return brandService.updateBrand(id, brand);
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long updatePartial(@PathVariable Long id, @Valid @RequestBody BrandRequestDTO category) {
		return brandService.partialUpdateBrand(id, category);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		brandService.deleteBrand(id);
	}

}
