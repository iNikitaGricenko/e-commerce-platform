package com.wolfhack.service.payment.controller;

import com.wolfhack.service.payment.model.dto.PaymentRequestDTO;
import com.wolfhack.service.payment.model.dto.PaymentResponseDTO;
import com.wolfhack.service.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody PaymentRequestDTO payment) {
        return paymentService.create(payment);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updatePartial(@PathVariable Long id, @Valid @RequestBody PaymentRequestDTO payment) {
        return paymentService.partialUpdate(id, payment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long update(@PathVariable Long id, @Valid @RequestBody PaymentRequestDTO payment) {
        return paymentService.update(id, payment);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentResponseDTO> getAll() {
        return paymentService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentResponseDTO getById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paymentService.delete(id);
    }

}
