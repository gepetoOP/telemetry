package com.telemetry.infra.repository.entity;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Serdeable
public class TransactionEntity {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal amount;
    private String operation_date;
}
