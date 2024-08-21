package com.wolfhack.service.catalog.controller;

import com.wolfhack.service.catalog.model.dto.InventoryRequestDTO;
import com.wolfhack.service.catalog.model.dto.InventoryResponseDTO;
import com.wolfhack.service.catalog.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/catalog/inventory")
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryService inventoryService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody InventoryRequestDTO inventory) {
		return inventoryService.create(inventory);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public InventoryResponseDTO getById(@PathVariable Long id) {
		return inventoryService.getById(id);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponseDTO> getAll() {
		return inventoryService.getAll();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long updateFull(@PathVariable Long id, @Valid @RequestBody InventoryRequestDTO inventory) {
		return inventoryService.update(id, inventory);
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long updatePartial(@PathVariable Long id, @Valid @RequestBody InventoryRequestDTO inventory) {
		return inventoryService.partialUpdate(id, inventory);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		inventoryService.delete(id);
	}

}
