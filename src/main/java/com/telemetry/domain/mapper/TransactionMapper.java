package com.telemetry.domain.mapper;

import com.telemetry.domain.model.Transaction;
import com.telemetry.infra.repository.entity.TransactionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jsr330")
public interface TransactionMapper {
    Transaction toTransactionResponse(TransactionEntity entity);

    List<Transaction> toTransactionResponse(List<TransactionEntity> entities);
}
