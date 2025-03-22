package com.telemetry.infra.entrypoint.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Serdeable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private BigDecimal amount;
    private String customerUuid;
    private LocalDateTime operationDate;
}


