package com.telemetry.infra.entrypoint;

import com.telemetry.domain.TransactionService;
import com.telemetry.infra.entrypoint.mapper.TransactionMapper;
import com.telemetry.infra.entrypoint.model.TransactionResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.opentelemetry.api.metrics.LongCounter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@Controller
@Tag(name = "Transactions", description = "Transactions management")
public class TransactionController {
    @Inject
    private LongCounter counter;
    @Inject
    private TransactionService transactionService;
    @Inject
    private TransactionMapper transactionMapper;

    @Operation(summary = "Lists all transactions", description = "Returns a list of all transactions")
    @ApiResponse(responseCode = "200", description = "Transaction list returned successfully")
    @Get("/transactions")
    public List<TransactionResponse> getTransactions() {
        var response = transactionService.getTransactions(UUID.randomUUID().toString());

        counter.add(1);

        return transactionMapper.toTransactionResponse(response);
    }
}
