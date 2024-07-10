package com.wolfhack.service.catalog.service;

import com.wolfhack.service.catalog.adapter.database.InventoryDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.InventoryMapper;
import com.wolfhack.service.catalog.model.domain.Inventory;
import com.wolfhack.service.catalog.model.dto.InventoryRequestDTO;
import com.wolfhack.service.catalog.model.dto.InventoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryMapper inventoryMapper;

	private final InventoryDatabaseAdapter inventoryDatabaseAdapter;

	public Long create(InventoryRequestDTO dto) {
		Inventory inventory = inventoryMapper.toModel(dto);

		return inventoryDatabaseAdapter.save(inventory);
	}

	public Long partialUpdate(Long id, InventoryRequestDTO dto) {
		Inventory inventory = inventoryMapper.toModel(dto);

		return inventoryDatabaseAdapter.partialUpdate(id, inventory);
	}

	public Long update(Long id, InventoryRequestDTO dto) {
		Inventory inventory = inventoryMapper.toModel(dto);

		return inventoryDatabaseAdapter.update(id, inventory);
	}

	public InventoryResponseDTO getById(Long id) {
		return inventoryMapper.toResponse(
			inventoryDatabaseAdapter.getById(id)
		);
	}

	public List<InventoryResponseDTO> getAll() {
		return inventoryDatabaseAdapter.getAll()
			.stream()
			.map(inventoryMapper::toResponse)
			.toList();
	}

	public void delete(Long id) {
		inventoryDatabaseAdapter.delete(id);
	}

}
