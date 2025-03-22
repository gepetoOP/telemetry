package com.telemetry.infra.repository;

import com.telemetry.infra.repository.entity.TransactionEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
