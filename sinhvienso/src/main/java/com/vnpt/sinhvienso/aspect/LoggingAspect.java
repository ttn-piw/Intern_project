package com.vnpt.sinhvienso.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
   Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.vnpt.sinhvienso.controller.*.*(..))")
    public void forControllerPackage(){}

    @Pointcut("execution(* com.vnpt.sinhvienso.service.*.*(..))")
    public void forServicePackage(){}

    @Pointcut("execution(* com.vnpt.sinhvienso.repository.*.*(..))")
    public void forRepositoryPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forRepositoryPackage()")
    public void forUserServiceFlow(){}

    @Before("forUserServiceFlow()")
    public void before(JoinPoint theJoinpoint){
        String methodCalling =  theJoinpoint.getSignature().toShortString();
        logger.info("@Before: calling method {}",methodCalling);

        Object[] args = theJoinpoint.getArgs();
        for(Object arg : args)
            logger.info("Argument: " + arg);
    }

    @AfterReturning(
            pointcut = "forUserServiceFlow()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String method = joinPoint.getSignature().toShortString();
        logger.info("@After calling method {}",method);

        logger.info("@After result: {}",result);
    }

    @AfterThrowing(
            pointcut = "forUserServiceFlow()",
            throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        String method = joinPoint.getSignature().toShortString();
        logger.error("Exception in method: {}",method + " with message: " + exception.getMessage());
    }


    //Get log with time
    @Around("forUserServiceFlow()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;

        logger.info("[TIMING] {} took {} ms", joinPoint.getSignature(), duration);

        return result;
    }

}
