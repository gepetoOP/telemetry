package com.telemetry.infra.entrypoint;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "Transactions", description = "Transactions management")
public class TransactionController {
    @Operation(summary = "Lists all transactions", description = "Returns a list of all transactions")
    @ApiResponse(responseCode = "200", description = "Transaction list returned successfully")
    @Get("/transactions")
    public String getTransactions() {
        return "Lista de usuários";
    }
}
