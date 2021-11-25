package com.example.bluecodepay.annotation;

import feign.FeignException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FeignHandler {
    Class<? extends Throwable>[] exceptions() default {Throwable.class};
}
