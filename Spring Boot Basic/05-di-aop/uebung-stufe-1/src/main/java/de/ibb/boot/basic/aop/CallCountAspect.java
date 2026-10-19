package de.ibb.boot.basic.aop;

import java.util.concurrent.atomic.AtomicInteger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CallCountAspect {
    private final AtomicInteger calls = new AtomicInteger();

    @Around("@annotation(de.ibb.boot.basic.aop.Counted)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO: calls.incrementAndGet();
        return joinPoint.proceed();
    }

    public int getCalls() {
        return calls.get();
    }

}
