package com.wolfhack.service.catalog.persistence;

import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.catalog.adapter.database.InventoryDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.InventoryMapper;
import com.wolfhack.service.catalog.model.domain.Inventory;
import com.wolfhack.service.catalog.model.entity.InventoryEntity;
import com.wolfhack.service.catalog.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryDatabaseGateway implements InventoryDatabaseAdapter {

	private final InventoryRepository inventoryRepository;

	private final InventoryMapper inventoryMapper;

	@Override
	public Long save(Inventory model) {
		InventoryEntity entity = inventoryMapper.toEntity(model);
		return inventoryRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Inventory model) {
		InventoryEntity updated = inventoryRepository.findById(id)
			.map(inventoryEntity -> inventoryMapper.partialUpdate(model, inventoryEntity))
			.orElseThrow(NotFoundException::new);

		return inventoryRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Inventory model) {
		if (!exists(id)) {
			throw new NotFoundException("Inventory item does not exist");
		}
		InventoryEntity entity = inventoryMapper.toEntity(model);
		inventoryMapper.update(model, entity);
		return inventoryRepository.save(entity).getId();
	}

	@Override
	public Inventory getById(Long id) {
		return inventoryRepository
			.findById(id)
			.map(inventoryMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return inventoryRepository.existsById(id);
	}

	@Override
	public Collection<Inventory> getById(Collection<Long> ids) {
		return inventoryRepository.findAllById(ids).stream().map(inventoryMapper::toModel).toList();
	}

	@Override
	public List<Inventory> getAll() {
		return inventoryRepository.findAll().stream().map(inventoryMapper::toModel).toList();
	}

	@Override
	public DomainPage<Inventory> getPage(Pageable pageable) {
		Page<Inventory> page = inventoryRepository.findAll(pageable).map(inventoryMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		inventoryRepository.deleteById(id);
	}

}
