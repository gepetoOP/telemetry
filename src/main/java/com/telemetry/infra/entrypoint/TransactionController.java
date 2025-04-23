package com.telemetry.infra.entrypoint;

import com.telemetry.domain.services.TransactionService;
import com.telemetry.infra.entrypoint.mapper.TransactionMapper;
import com.telemetry.infra.entrypoint.model.CreateTransactionRequest;
import com.telemetry.infra.entrypoint.model.TransactionResponse;
import com.telemetry.infra.repository.entity.TransactionEntity;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Tag(name = "Transactions", description = "Transactions management")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class TransactionController {
    @Inject
    private TransactionService transactionService;
    @Inject
    private TransactionMapper transactionMapper;

    @Operation(summary = "Lists all transactions", description = "Returns a list of all transactions")
    @ApiResponse(responseCode = "200", description = "Transaction list returned successfully")
    @Get("/transactions")
    @WithSpan
    @Secured({"ROLE_ADMIN"})
    public List<TransactionResponse> getTransactions(@QueryValue("customerUuid") String customerUuid) {
        var response = transactionService.getTransactions(customerUuid);

        return transactionMapper.toTransactionResponse(response);
    }

    @Operation(summary = "Save a transaction", description = "Saves a transaction")
    @ApiResponse(responseCode = "200", description = "Saved successfully")
    @Post("/transactions")
    @WithSpan
    @Secured({"ROLE_USER"})
    public TransactionEntity saveTransactions(@Body CreateTransactionRequest request) {
        return transactionService.saveTransaction(request.getAmount(), request.getCustomerUuid());
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @View("home")
    @Get
    public Map<String, Object> index() {
        return new HashMap<>();
    }
}
