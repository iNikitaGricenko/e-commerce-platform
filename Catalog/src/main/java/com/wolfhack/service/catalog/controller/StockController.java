package com.wolfhack.service.catalog.controller;

import com.wolfhack.service.catalog.model.dto.StockRequestDTO;
import com.wolfhack.service.catalog.model.dto.StockResponseDTO;
import com.wolfhack.service.catalog.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalog/stocks")
@RequiredArgsConstructor
public class StockController {

	private final StockService stockService;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public StockResponseDTO getById(@PathVariable Long id) {
		return stockService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@Valid @RequestBody StockRequestDTO stock) {
		return stockService.create(stock);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long update(@PathVariable Long id, @Valid @RequestBody StockRequestDTO stock) {
		return stockService.update(id, stock);
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long updatePartial(@PathVariable Long id, @Valid @RequestBody StockRequestDTO category) {
		return stockService.partialUpdate(id, category);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		stockService.delete(id);
	}

}
