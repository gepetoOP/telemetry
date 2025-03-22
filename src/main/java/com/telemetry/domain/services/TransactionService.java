package com.telemetry.domain.services;

import com.telemetry.domain.model.Transaction;
import com.telemetry.infra.repository.entity.TransactionEntity;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    TransactionEntity saveTransaction(BigDecimal amount, String customerUuid);
    List<Transaction> getTransactions(String customerUuid);
}
