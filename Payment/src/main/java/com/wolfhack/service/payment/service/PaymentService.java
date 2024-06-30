package com.wolfhack.service.payment.service;

import com.wolfhack.service.payment.adapter.database.PaymentDatabaseAdapter;
import com.wolfhack.service.payment.adapter.payment.PaymentProcessorAdapter;
import com.wolfhack.service.payment.adapter.payment.processors.model.PaymentProcess;
import com.wolfhack.service.payment.mapper.PaymentMapper;
import com.wolfhack.service.payment.model.domain.Payment;
import com.wolfhack.service.payment.model.dto.PaymentRequestDTO;
import com.wolfhack.service.payment.model.dto.PaymentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentMapper paymentMapper;

	private final PaymentDatabaseAdapter paymentDatabaseAdapter;

	private final PaymentProcessorAdapter paymentProcessor;

    public Long create(PaymentRequestDTO dto) {
	    Payment payment = paymentMapper.toModel(dto);

	    PaymentProcess status = paymentProcessor.process(payment);

		if (status.equals(PaymentProcess.FAILURE)) {
			throw new RuntimeException("Payment failed");
		}

	    return paymentDatabaseAdapter.save(payment);
    }

    public Long partialUpdate(Long id, PaymentRequestDTO dto) {
	    Payment payment = paymentMapper.toModel(dto);

	    return paymentDatabaseAdapter.partialUpdate(id, payment);
    }

    public Long update(Long id, PaymentRequestDTO dto) {
	    Payment payment = paymentMapper.toModel(dto);

        return paymentDatabaseAdapter.update(id, payment);
    }

    public List<PaymentResponseDTO> getAll() {
        return paymentDatabaseAdapter.getAll()
	        .stream()
	        .map(paymentMapper::toResponse)
	        .toList();
    }

    public PaymentResponseDTO getById(Long id) {
	    Payment payment = paymentDatabaseAdapter.getById(id);

	    return paymentMapper.toResponse(payment);
    }

    public void cancel(Long id) {
	    Payment payment = paymentDatabaseAdapter.getById(id);

	    PaymentProcess status = paymentProcessor.cancel(payment);

	    if (status.equals(PaymentProcess.FAILURE)) {
			throw new RuntimeException("Payment cancellation failed");
		}

	    paymentDatabaseAdapter.save(payment);
    }

    public void delete(Long id) {
        paymentDatabaseAdapter.delete(id);
    }

}
