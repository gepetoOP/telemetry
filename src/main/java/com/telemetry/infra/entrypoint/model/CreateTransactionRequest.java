package com.telemetry.infra.entrypoint.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@Serdeable
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequest {
    private BigDecimal amount;
}
