package com.telemetry.domain.services;

import com.telemetry.domain.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions(String customerUuid);
}
