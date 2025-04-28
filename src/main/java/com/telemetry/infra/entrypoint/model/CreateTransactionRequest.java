package com.telemetry.infra.entrypoint.model;

import com.telemetry.annotation.SpanAttributes;
import io.micronaut.core.annotation.Introspected;
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
    @SpanAttributes("amount.teste")
    private BigDecimal amount;
    private String customerUuid;
}
