package com.telemetry.annotation.interceptor;

import com.telemetry.annotation.SpanAttributes;
import com.telemetry.infra.entrypoint.model.CreateTransactionRequest;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.context.annotation.Bean;
import io.micronaut.core.annotation.AnnotationMetadata;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanProperty;
import io.micronaut.core.beans.BeanWrapper;
import io.micronaut.core.type.Argument;
import io.micronaut.core.value.ValueResolver;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
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
        } finally {
            span.end();
        }

        return context.proceed();
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
                Object parameterValue = parameterValues[i];
                var introspection = BeanIntrospection.getIntrospection(parameterValue.getClass());

                for (BeanProperty<?, ?> property : introspection.getBeanProperties()) {
                    var annotation = property.getAnnotation(SpanAttributes.class);

                    if (annotation != null) {
                        Object value = ((BeanProperty<Object, ?>) property).get(parameterValue);
                        span.setAttribute(String.valueOf(annotation.getValues().get("value")), String.valueOf(value));
                    }
                }
            }
        }
    }
}
