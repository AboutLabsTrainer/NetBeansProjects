package com.example.helloworldapi.aspect;

import com.example.helloworldapi.annotation.Logged;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Around-Advice für Methoden mit der Logged-Markierung.
 * Der Proxy des Spring-Containers ruft zuerst diesen Aspect auf und danach die eigentliche Service-Methode.
 */
@Aspect
@Component
public class LoggedAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggedAspect.class);

    @Around("@annotation(logged)")
    public Object logMethodCall(ProceedingJoinPoint joinPoint, Logged logged) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String level = logged.level();

        log.info("[{}] → Calling {}.{}", level, className, methodName);

        Object result = joinPoint.proceed();

        log.info("[{}] ← Finished {}.{}", level, className, methodName);

        return result;
    }
}
