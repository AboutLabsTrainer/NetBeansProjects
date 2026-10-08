package com.example.helloworldapi.aspect;

import com.example.helloworldapi.annotation.ValidateNotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Prüft vor dem Methodenaufruf die Argumente, die ValidateNotNull nennt.
 * Ein Before-Advice kann den Aufruf ablehnen, bevor die Service-Methode betreten wird.
 */
@Aspect
@Component
public class ValidationAspect {

    private static final Logger log = LoggerFactory.getLogger(ValidationAspect.class);

    @Before("@annotation(validateNotNull)")
    public void validateParameters(JoinPoint joinPoint, ValidateNotNull validateNotNull) {
        Object[] args = joinPoint.getArgs();
        int[] indices = validateNotNull.parameterIndices();

        for (int index : indices) {
            if (index < args.length && args[index] == null) {
                String methodName = joinPoint.getSignature().getName();
                log.error("Validation failed: {}: Parameter at index {} must not be null!", methodName, index);
                throw new IllegalArgumentException(
                        methodName + ": Parameter at index " + index + " must not be null!");
            }
        }
    }
}
