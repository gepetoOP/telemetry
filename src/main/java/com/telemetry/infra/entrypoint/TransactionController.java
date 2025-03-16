package com.telemetry.infra.entrypoint;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

@Controller
@Tag(name = "Transactions", description = "Transactions management")
public class TransactionController {
    @Inject
    private OpenTelemetry openTelemetry;
    @Operation(summary = "Lists all transactions", description = "Returns a list of all transactions")
    @ApiResponse(responseCode = "200", description = "Transaction list returned successfully")
    @Get("/transactions")
    public String getTransactions() {
        Tracer tracer = openTelemetry.getTracer("io.opentelemetry.example");

        var teste = tracer.spanBuilder("exampleSpan").startSpan();

        teste.setAttribute("good", true);
        teste.setStatus(StatusCode.OK);

        teste.end();

        return "Lista de usuários";
    }
}
