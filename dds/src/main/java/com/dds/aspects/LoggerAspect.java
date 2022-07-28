package com.dds.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
    private static final Logger LOGGER = LogManager.getLogger(LoggerAspect.class);

    //public void logControllers
}
