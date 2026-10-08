package com.fleetdata.personmanagement.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Löst einen Methodenparameter zum angemeldeten Domain-User auf.
 * Der Ausdruck liest den Namen aus der Authentication und holt den User über den UserService.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal(expression = "@userService.getUserByUsername(authentication.name)")
public @interface CurrentUser {
}
