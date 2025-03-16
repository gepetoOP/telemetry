package com.telemetry.infra.entrypoint;

import com.telemetry.domain.Transaction;
import com.telemetry.domain.TransactionService;
import com.telemetry.infra.entrypoint.mapper.TransactionMapper;
import com.telemetry.infra.entrypoint.model.TransactionResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.LongCounterBuilder;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@Tag(name = "Transactions", description = "Transactions management")
public class TransactionController {
    @Inject
    private OpenTelemetry openTelemetry;
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
        Tracer tracer = openTelemetry.getTracer("io.opentelemetry.example");

        var teste = tracer.spanBuilder("exampleSpan").startSpan();

        teste.setAttribute("good", true);
        teste.setStatus(StatusCode.OK);

        teste.end();

        var response = transactionService.getTransactions();

        counter.add(1);

        return transactionMapper.toTransactionResponse(response);
    }
}
