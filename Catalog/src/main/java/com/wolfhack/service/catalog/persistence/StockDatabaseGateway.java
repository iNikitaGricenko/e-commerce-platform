package com.wolfhack.service.catalog.persistence;

import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.catalog.adapter.database.StockDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.StockMapper;
import com.wolfhack.service.catalog.model.domain.Stock;
import com.wolfhack.service.catalog.model.entity.StockEntity;
import com.wolfhack.service.catalog.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockDatabaseGateway implements StockDatabaseAdapter {

	private final StockRepository stockRepository;

	private final StockMapper stockMapper;

	@Override
	public Long save(Stock model) {
		StockEntity entity = stockMapper.toEntity(model);
		return stockRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Stock model) {
		StockEntity updated = stockRepository.findById(id)
			.map(stockEntity -> stockMapper.partialUpdate(model, stockEntity))
			.orElseThrow(NotFoundException::new);

		return stockRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Stock model) {
		if (!exists(id)) {
			throw new NotFoundException("Stock item does not exist");
		}
		StockEntity entity = stockMapper.toEntity(model);
		stockMapper.update(model, entity);
		return stockRepository.save(entity).getId();
	}

	@Override
	public Stock getById(Long id) {
		return stockRepository
			.findById(id)
			.map(stockMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return stockRepository.existsById(id);
	}

	@Override
	public Collection<Stock> getById(Collection<Long> ids) {
		return stockRepository.findAllById(ids).stream().map(stockMapper::toModel).toList();
	}

	@Override
	public List<Stock> getAll() {
		return stockRepository.findAll().stream().map(stockMapper::toModel).toList();
	}

	@Override
	public DomainPage<Stock> getPage(Pageable pageable) {
		Page<Stock> page = stockRepository.findAll(pageable).map(stockMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		stockRepository.deleteById(id);
	}

}
