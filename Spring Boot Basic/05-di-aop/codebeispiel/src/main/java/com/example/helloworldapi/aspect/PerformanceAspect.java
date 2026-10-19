package com.example.helloworldapi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Misst Methoden, die mit Timed markiert sind.
 * Der Around-Advice liegt um den echten Aufruf und kann die Dauer deshalb vor und nach proceed festhalten.
 */
@Aspect
@Component
public class PerformanceAspect {

    private static final Logger log = LoggerFactory.getLogger(PerformanceAspect.class);

    @Around("@annotation(com.example.helloworldapi.annotation.Timed)")
    public Object measurePerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        long start = System.currentTimeMillis();

        Object result = null;
        try {
            result = joinPoint.proceed();

            long duration = System.currentTimeMillis() - start;
            log.info("⏱️ {}.{} took {}ms", className, methodName, duration);

        } catch (Exception e) {
            long duration = System.currentTimeMillis() - start;
            log.error("❌ {}.{} failed after {}ms", className, methodName, duration);
            throw e;
        }

        return result;
    }
}
