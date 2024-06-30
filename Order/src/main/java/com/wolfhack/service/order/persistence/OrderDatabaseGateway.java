package com.wolfhack.service.order.persistence;

import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.order.adapter.database.OrderDatabaseAdapter;
import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.service.order.mapper.OrderMapper;
import com.wolfhack.service.order.model.domain.Order;
import com.wolfhack.service.order.model.entity.OrderEntity;
import com.wolfhack.service.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDatabaseGateway implements OrderDatabaseAdapter {

	private final OrderRepository orderRepository;

	private final OrderMapper orderMapper;

	@Override
	public Long save(Order model) {
		OrderEntity entity = orderMapper.toEntity(model);

		return orderRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Order model) {
		OrderEntity updated = orderRepository.findById(id)
			.map(categoryEntity -> orderMapper.partialUpdate(model, categoryEntity))
			.orElseThrow(NotFoundException::new);

		return orderRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Order model) {
		if (!exists(id)) {
			throw new NotFoundException("Order does not exist");
		}

		OrderEntity entity = orderMapper.toEntity(model);
		orderMapper.update(model, entity);
		return orderRepository.save(entity).getId();
	}

	@Override
	public Order getById(Long id) {
		return orderRepository
			.findById(id)
			.map(orderMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return orderRepository.existsById(id);
	}

	@Override
	public Collection<Order> getById(Collection<Long> ids) {
		return orderRepository
			.findAllById(ids)
			.stream()
			.map(orderMapper::toModel)
			.toList();
	}

	@Override
	public List<Order> getAll() {
		return orderRepository
			.findAll()
			.stream()
			.map(orderMapper::toModel)
			.toList();
	}

	@Override
	public DomainPage<Order> getPage(Pageable pageable) {
		Page<Order> page = orderRepository
			.findAll(pageable)
			.map(orderMapper::toModel);

		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public List<Order> getByUser(Long userId) {
		return orderRepository
			.findByUserId(userId)
			.stream()
			.map(orderMapper::toModel)
			.toList();
	}

}
