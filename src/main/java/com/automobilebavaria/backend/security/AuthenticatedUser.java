package com.automobilebavaria.backend.security;

import com.automobilebavaria.backend.config.argumentresolver.AuthenticatedUserArgumentResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation for injecting the authenticated user into a controller method.
 * @see AuthenticatedUserArgumentResolver
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface AuthenticatedUser {
}
