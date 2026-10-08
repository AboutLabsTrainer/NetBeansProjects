package com.example.monitoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Around-Advice um Repository-Methoden.
 * Er misst den Aufruf und gibt das Ergebnis an den Query-Tracker weiter. Die Fachmethode selbst bleibt unverändert.
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class RepositoryMonitoringAspect {

    private final DatabaseQueryTracker queryTracker;

    @Around("execution(* com.example.repository..*(..))")
    public Object monitorRepositoryMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String repository = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String method = joinPoint.getSignature().getName();

        long startTime = System.currentTimeMillis();
        boolean success = true;

        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            success = false;
            throw e;
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            queryTracker.recordQuery(repository, method, duration, success);

            if (duration > 1000) {
                log.warn("Slow query detected: {}.{} took {}ms", repository, method, duration);
            }
        }
    }
}
