package com.telemetry.domain.services.impl;

import com.telemetry.annotation.SpanAttributes;
import com.telemetry.domain.mapper.TransactionMapper;
import com.telemetry.domain.model.Transaction;
import com.telemetry.domain.services.TransactionService;
import com.telemetry.infra.repository.TransactionRepository;
import com.telemetry.infra.repository.entity.TransactionEntity;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Singleton
public class TransactionServiceImpl implements TransactionService {
    @Inject
    private TransactionMapper transactionMapper;
    @Inject
    private TransactionRepository transactionRepository;

    @Override
    @WithSpan
    public TransactionEntity saveTransaction(BigDecimal amount, String customerUuid) {
        var entity = new TransactionEntity();
        entity.setAmount(amount);
        entity.setCustomerUuid(UUID.fromString(customerUuid));
        entity.setUuid(UUID.randomUUID());
        entity.setOperationDate(LocalDateTime.now());

        return transactionRepository.save(entity);
    }

    @Override
    @WithSpan
    public List<Transaction> getTransactions(@SpanAttributes("customer.uuid") String customerUuid) {
        var response = transactionRepository.findByCustomerUuid(UUID.fromString(customerUuid));

        return transactionMapper.toTransactionResponse(response);
    }
}
