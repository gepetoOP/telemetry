package com.telemetry.infra.repository;

import com.telemetry.infra.repository.entity.TransactionEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByCustomerUuid(UUID customerUuid);
}
