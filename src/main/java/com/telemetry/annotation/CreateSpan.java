package com.telemetry.annotation;

import com.telemetry.annotation.interceptor.CreateSpanInterceptor;
import io.micronaut.aop.Around;
import io.micronaut.context.annotation.Type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Around
@Type(CreateSpanInterceptor.class)
public @interface CreateSpan {
}
