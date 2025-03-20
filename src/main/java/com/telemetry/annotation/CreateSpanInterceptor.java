package com.telemetry.annotation;

import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.annotation.Nullable;
import io.opentelemetry.api.trace.Tracer;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CreateSpanInterceptor implements MethodInterceptor<Object, Object> {
    @Inject
    private Tracer tracer;

    @Override
    public @Nullable Object intercept(MethodInvocationContext<Object, Object> context) {
        var className = context.getDeclaringType().getName();
        var methodName = context.getMethodName();

        var span = tracer.spanBuilder(className + "." + methodName).startSpan();

        try {
            return context.proceed();
        } finally {
            span.end();
        }
    }
}
