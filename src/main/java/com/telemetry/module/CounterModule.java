package com.telemetry.module;

import io.micronaut.context.annotation.Factory;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.LongCounter;
import jakarta.inject.Singleton;

@Factory
public class CounterModule {
    @Singleton
    public LongCounter getCounter(OpenTelemetry openTelemetry) {
        return openTelemetry.getMeter("io.opentelemetry.example").counterBuilder("api.calls").setDescription("Quantidade de chamadas de APIs").build();
    }
}
