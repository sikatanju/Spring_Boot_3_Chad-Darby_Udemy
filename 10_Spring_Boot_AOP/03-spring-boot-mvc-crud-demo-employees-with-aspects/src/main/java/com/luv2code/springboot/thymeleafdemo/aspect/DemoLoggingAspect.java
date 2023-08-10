package com.luv2code.springboot.thymeleafdemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage()    {}

    // do the pointcut declarations for other packages as well (service & dao)
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage()    {}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage()    {}

    // Combining pointcut declarations
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow()   {}

    // adding add before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        // display method name
        myLogger.info("--->>> in @Before, calling method : " + joinPoint.getSignature().toShortString());
        // System.out.println("---> Before advice, method name : " + joinPoint.getSignature().toShortString());

        // display the arguments to the method

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // and loop through those args
        for (var temp : args)
            myLogger.info("--->> argument : " + temp);
    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result)  {
        // display method we are returning from
        myLogger.info("--->>> in @AfterReturning, calling method : " + joinPoint.getSignature().toShortString());

        // display data that was returned.
        myLogger.info("--->>> Result : " + result);
    }

}
