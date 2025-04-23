package com.telemetry.annotation.interceptor;

import com.telemetry.annotation.SpanAttributes;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.annotation.AnnotationMetadata;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.type.Argument;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Singleton
public class CreateSpanInterceptor implements MethodInterceptor<Object, Object> {
    private Tracer tracer = GlobalOpenTelemetry.getTracer("x");

    @Override
    public @Nullable Object intercept(MethodInvocationContext<Object, Object> context) {
        var className = context.getDeclaringType().getName();
        var methodName = context.getMethodName();

        var span = tracer.spanBuilder(className + "." + methodName).startSpan();

        try {
            enrichSpan(context, span);

            return context.proceed();
        } finally {
            span.end();
        }
    }

    public static void enrichSpan(MethodInvocationContext<?, ?> context, Span span) {
        Argument<?>[] arguments = context.getArguments();
        Object[] parameterValues = context.getParameterValues();

        for (int i = 0; i < arguments.length; i++) {
            Argument<?> argument = arguments[i];
            AnnotationMetadata annotationMetadata = argument.getAnnotationMetadata();

            if (annotationMetadata.hasAnnotation(SpanAttributes.class)) {
                Object value = parameterValues[i];

                if (value != null) {
                    String attributeName = annotationMetadata.stringValue(SpanAttributes.class).orElse(argument.getName());
                    span.setAttribute(attributeName, value.toString());
                }
            }
            else {
                List<String> attributes = Arrays.stream(parameterValues[i].getClass().getDeclaredFields())
                        .map(field -> field.getAnnotation(SpanAttributes.class))
                        .filter(Objects::nonNull)
                        .map(filter -> filter.value())
                        .toList();

                var x = 2;
            }
        }
    }
}
