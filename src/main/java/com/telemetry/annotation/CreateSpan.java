package com.telemetry.annotation;

import io.micronaut.aop.Around;
import io.micronaut.context.annotation.Type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // A anotação deve estar disponível em tempo de execução
@Around // Indica que esta anotação será usada para AOP
@Type(CreateSpanInterceptor.class)
public @interface CreateSpan {
}
