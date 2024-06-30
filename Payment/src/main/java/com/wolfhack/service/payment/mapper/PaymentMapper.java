package com.wolfhack.service.payment.mapper;

import com.wolfhack.service.payment.model.domain.Payment;
import com.wolfhack.service.payment.model.dto.PaymentRequestDTO;
import com.wolfhack.service.payment.model.dto.PaymentResponseDTO;
import com.wolfhack.service.payment.model.entity.PaymentEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

	PaymentEntity toEntity(Payment payment);

	Payment toModel(PaymentEntity paymentEntity);

	Payment toModel(PaymentRequestDTO dto);

	PaymentResponseDTO toResponse(Payment dto);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	PaymentEntity partialUpdate(Payment paymentRequestDTO, @MappingTarget PaymentEntity paymentEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	PaymentEntity update(Payment paymentRequestDTO, @MappingTarget PaymentEntity paymentEntity);

}