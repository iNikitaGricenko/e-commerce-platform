package com.wolfhack.service.catalog.controller;

import com.wolfhack.service.catalog.model.dto.InventoryRequestDTO;
import com.wolfhack.service.catalog.model.dto.InventoryResponseDTO;
import com.wolfhack.service.catalog.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/catalog/categories")
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryService inventoryService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody InventoryRequestDTO inventory) {
		return inventoryService.createInventory(inventory);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public InventoryResponseDTO getById(@PathVariable Long id) {
		return inventoryService.getInventoryById(id);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponseDTO> getAll() {
		return inventoryService.getAllCategories();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long updateFull(@PathVariable Long id, @Valid @RequestBody InventoryRequestDTO inventory) {
		return inventoryService.updateInventory(id, inventory);
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long updatePartial(@PathVariable Long id, @Valid @RequestBody InventoryRequestDTO inventory) {
		return inventoryService.partialUpdateInventory(id, inventory);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		inventoryService.deleteInventory(id);
	}

}
