package com.dds.aspects;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggerAspect {
    private static final Logger LOGGER = LogManager.getLogger(LoggerAspect.class);
    private final StopWatch stopWatch = new StopWatch();

    @SneakyThrows
    @Around("execution(* com.dds.controllers..*(..))")
    public Object logControllers(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        LOGGER.info("Controller's "
                + methodSignature.getDeclaringType().getSimpleName()
                + " method " + methodSignature.getName()
                + " execution time: "
                + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }
}
