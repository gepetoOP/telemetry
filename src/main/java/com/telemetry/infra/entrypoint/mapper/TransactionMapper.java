package com.telemetry.infra.entrypoint.mapper;

import com.telemetry.domain.model.Transaction;
import com.telemetry.infra.entrypoint.model.TransactionResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jsr330")
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);

    List<TransactionResponse> toTransactionResponse(List<Transaction> transactions);
}
