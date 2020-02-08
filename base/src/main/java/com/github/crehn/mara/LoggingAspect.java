package com.github.crehn.mara;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    @Around("within(@Logged *)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        try {
            logger.info("calling {}", methodInvocation(joinPoint));
            Object result = joinPoint.proceed();
            logger.info("called {}", joinPoint.getSignature().getName());
            logger.debug("called {} returning {}", joinPoint.getSignature().getName(), result);
            return result;
        } catch (Exception e) {
            logger.info("failed {} with Exception {}", joinPoint.getSignature().getName(), e.toString());
            throw e;
        }
    }

    private String methodInvocation(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().getName() + "(" + List.of(joinPoint.getArgs()) + ")";
    }
}
