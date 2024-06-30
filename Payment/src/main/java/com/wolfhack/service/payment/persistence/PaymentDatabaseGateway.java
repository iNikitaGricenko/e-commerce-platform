package com.wolfhack.service.payment.persistence;

import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.payment.adapter.database.PaymentDatabaseAdapter;
import com.wolfhack.service.payment.mapper.PaymentMapper;
import com.wolfhack.service.payment.model.domain.Payment;
import com.wolfhack.service.payment.model.entity.PaymentEntity;
import com.wolfhack.service.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentDatabaseGateway implements PaymentDatabaseAdapter {

	private final PaymentRepository paymentRepository;

	private final PaymentMapper paymentMapper;

	@Override
	public Long save(Payment model) {
		PaymentEntity entity = paymentMapper.toEntity(model);

		return paymentRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Payment model) {
		PaymentEntity updated = paymentRepository.findById(id)
			.map(categoryEntity -> paymentMapper.partialUpdate(model, categoryEntity))
			.orElseThrow(NotFoundException::new);

		return paymentRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Payment model) {
		if (!exists(id)) {
			throw new NotFoundException("Payment does not exist");
		}

		PaymentEntity entity = paymentMapper.toEntity(model);
		paymentMapper.update(model, entity);
		return paymentRepository.save(entity).getId();
	}

	@Override
	public Payment getById(Long id) {
		return paymentRepository
			.findById(id)
			.map(paymentMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return paymentRepository.existsById(id);
	}

	@Override
	public Collection<Payment> getById(Collection<Long> ids) {
		return paymentRepository
			.findAllById(ids)
			.stream()
			.map(paymentMapper::toModel)
			.toList();
	}

	@Override
	public List<Payment> getAll() {
		return paymentRepository
			.findAll()
			.stream()
			.map(paymentMapper::toModel)
			.toList();
	}

	@Override
	public DomainPage<Payment> getPage(Pageable pageable) {
		Page<Payment> page = paymentRepository
			.findAll(pageable)
			.map(paymentMapper::toModel);

		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		paymentRepository.deleteById(id);
	}

}
