package com.telemetry.domain.impl;

import com.telemetry.annotation.CreateSpan;
import com.telemetry.annotation.SpanAttributes;
import com.telemetry.domain.Transaction;
import com.telemetry.domain.TransactionService;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Singleton
public class TransactionServiceImpl implements TransactionService {
    @Override
//    @CreateSpan
    public List<Transaction> getTransactions(@SpanAttributes("customer.uuid") String customerUuid) {
        return List.of(
                Transaction.builder().uuid(UUID.randomUUID().toString()).value(BigDecimal.TEN).build(),
                Transaction.builder().uuid(UUID.randomUUID().toString()).value(BigDecimal.TWO).build()
        );
    }
}
