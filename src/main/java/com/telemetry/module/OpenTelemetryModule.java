package com.telemetry.module;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;
import jakarta.inject.Singleton;


@Factory
public class OpenTelemetryModule {
    @Singleton
    public OpenTelemetry initOpenTelemetry() {
        return AutoConfiguredOpenTelemetrySdk.initialize().getOpenTelemetrySdk();
    }

    @Singleton
    @Requires(classes = OpenTelemetry.class)
    public LongCounter getCounter(OpenTelemetry openTelemetry, @Value("${micronaut.application.name}") String applicationName) {
        return openTelemetry.getMeter(applicationName).counterBuilder("api.calls").setDescription("Quantidade de chamadas de APIs").build();
    }

    @Singleton
    @Requires(classes = OpenTelemetry.class)
    public Tracer getTracer(OpenTelemetry openTelemetry, @Value("${micronaut.application.name}") String applicationName) {
        return openTelemetry.getTracer(applicationName);
    }
}
