package de.ibb.boot.ak.security.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Löst einen Methodenparameter zum angemeldeten Domain-User auf.
 * Der Ausdruck wird auf dem Principal (UserDetails) ausgewertet: username liest dessen Namen,
 * anschließend wird der User über den UserService geholt.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal(expression = "@userService.getUserByUsername(username)")
public @interface CurrentUser {
}
