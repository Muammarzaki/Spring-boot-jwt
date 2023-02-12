package com.belajarjwt.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class LogAuth {
    @Pointcut("execution(* com.belajarjwt.controllers.AuthController.*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void loginlogging(JoinPoint jPoint) {
        log.info("someone tries to login :: {}", jPoint.getSignature());
    }

    @After("logPointCut()")
    public void afterLog(JoinPoint jPoint) {
        log.info("login finish ::", jPoint.getSignature());
    }

    @Around("logPointCut()")
    public Object aroundLogin(ProceedingJoinPoint jPoint) throws Throwable {
        log.info("around Login ::", jPoint.getSignature());
        return jPoint.proceed();
    }

    // @AfterReturning
    // @AfterThrowing
}
