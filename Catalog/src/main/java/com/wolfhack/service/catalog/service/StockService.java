package com.wolfhack.service.catalog.service;

import com.wolfhack.service.catalog.adapter.database.StockDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.StockMapper;
import com.wolfhack.service.catalog.model.domain.Stock;
import com.wolfhack.service.catalog.model.dto.StockRequestDTO;
import com.wolfhack.service.catalog.model.dto.StockResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

	private final StockMapper stockMapper;

	private final StockDatabaseAdapter stockDatabaseAdapter;

	private final KafkaTemplate<String, String> kafkaTemplate;

	public Long create(StockRequestDTO dto) {
		Stock stock = stockMapper.toModel(dto);

		Long savedStockId = stockDatabaseAdapter.save(stock);

		kafkaTemplate.send("inventory-updates", "Stock saved: " + savedStockId);

		return savedStockId;
	}

	public Long partialUpdate(Long id, StockRequestDTO dto) {
		Stock stock = stockMapper.toModel(dto);

		return stockDatabaseAdapter.partialUpdate(id, stock);
	}

	public Long update(Long id, StockRequestDTO dto) {
		Stock stock = stockMapper.toModel(dto);

		return stockDatabaseAdapter.update(id, stock);
	}

	public StockResponseDTO getById(Long id) {
		return stockMapper.toResponse(
			stockDatabaseAdapter.getById(id)
		);
	}

	public void delete(Long id) {
		stockDatabaseAdapter.delete(id);
		kafkaTemplate.send("inventory-updates", "Stock deleted: " + id);
	}

}
