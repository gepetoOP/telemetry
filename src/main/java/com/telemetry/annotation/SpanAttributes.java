package com.telemetry.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Inherited
@Target({PARAMETER, ANNOTATION_TYPE, FIELD})
public @interface SpanAttributes {
    String value() default "";
}
