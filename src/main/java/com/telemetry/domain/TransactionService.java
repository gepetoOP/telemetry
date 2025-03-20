package com.telemetry.domain;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions(String customerUuid);
}
