package com.telemetry.module;

import io.micronaut.context.annotation.Factory;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.trace.Tracer;
import jakarta.inject.Singleton;

@Factory
public class SignalModule {
    @Singleton
    public LongCounter getCounter(OpenTelemetry openTelemetry) {
        return openTelemetry.getMeter("io.opentelemetry.example").counterBuilder("api.calls").setDescription("Quantidade de chamadas de APIs").build();
    }

    @Singleton
    public Tracer getTracer(OpenTelemetry openTelemetry) {
        return openTelemetry.getTracer("io.opentelemetry.example");
    }
}
