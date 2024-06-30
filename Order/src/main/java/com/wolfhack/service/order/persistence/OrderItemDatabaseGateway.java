package com.wolfhack.service.order.persistence;

import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.order.adapter.database.OrderItemDatabaseAdapter;
import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.service.order.mapper.OrderItemMapper;
import com.wolfhack.service.order.model.domain.OrderItem;
import com.wolfhack.service.order.model.entity.OrderItemEntity;
import com.wolfhack.service.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemDatabaseGateway implements OrderItemDatabaseAdapter {

	private final OrderItemRepository orderItemRepository;
	private final OrderItemMapper orderItemMapper;

	@Override
	public Long save(OrderItem model) {
		OrderItemEntity entity = orderItemMapper.toEntity(model);
		return orderItemRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, OrderItem model) {
		OrderItemEntity updated = orderItemRepository.findById(id)
				.map(categoryEntity -> orderItemMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return orderItemRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, OrderItem model) {
		if (!exists(id)) {
			throw new NotFoundException("Order item does not exist");
		}
		OrderItemEntity entity = orderItemMapper.toEntity(model);
		orderItemMapper.update(model, entity);
		return orderItemRepository.save(entity).getId();
	}

	@Override
	public OrderItem getById(Long id) {
		return orderItemRepository
			.findById(id)
			.map(orderItemMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return orderItemRepository.existsById(id);
	}

	@Override
	public Collection<OrderItem> getById(Collection<Long> ids) {
		return orderItemRepository.findAllById(ids).stream().map(orderItemMapper::toModel).toList();
	}

	@Override
	public List<OrderItem> getAll() {
		return orderItemRepository.findAll().stream().map(orderItemMapper::toModel).toList();
	}

	@Override
	public DomainPage<OrderItem> getPage(Pageable pageable) {
		Page<OrderItem> page = orderItemRepository.findAll(pageable).map(orderItemMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		orderItemRepository.deleteById(id);
	}
}
