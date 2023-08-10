package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {



    @Around("execution (* com.luv2code.aopdemo.service.*.getUpdate(..))")
    public Object aroundGetUpdate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable   {
        // print out method we are advising on
        System.out.println("\n---> Executing @Around on method : " + proceedingJoinPoint.getSignature().toShortString());

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        }
        catch (Exception exc)   {
            // log the exception
            System.out.println(exc.getMessage());

            // give user a custom message, when we don't want to throw exception to the main app.
            // result = "Major Accident! But no worries, you private AOP will help you out! :)";

            // Now, we want to throw the exception back to the main app.
            throw exc;
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;

        System.out.println("\n----> Total Duration : " + duration/1000.0 + " seconds");

        return result;
    }


    @After("execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        System.out.println("\n---> Executing @After (finally) on method : " + joinPoint.getSignature().toShortString());
    }


    // Add a new advice for @AfterThrowing on the findAccounts method
    @AfterThrowing(
            pointcut = "execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc)  {
        System.out.println("\n---> Executing @AfterThrowing on method : " + joinPoint.getSignature());

        System.out.println("\n----> The exception is : " + exc);
    }

    // add a new advice for @AfterReturning on the findAccounts method
//    @AfterReturning(
//            pointcut = "execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
//            returning = "result"
//    )
//    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result)  {
//        // Print out which method we are advising on
//        // String method = joinPoint.getSignature().toShortString();
//        System.out.println("\n---> Executing @AfterReturning on method : " + joinPoint.getSignature().toShortString());
//
//        System.out.println("\n---> Result is : " + result);
//
//        // Let's post-process the data ... Let's modify it :)
//
//        // convert the account names to uppercase
//        convertAccountNamesToUppercase(result);
//
//        System.out.println("\n----- Result is : " + result);
//    }

    private void convertAccountNamesToUppercase(List<Account> result) {
        for (Account account : result)
            account.setName(account.getName().toUpperCase());
    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackage()")
    public void forDAO(JoinPoint joinPoint)   { // *i
        System.out.println(getClass() + "---->>> Executing before any method under \"dao\" package :)");
        // Display the method signature
        // *i. To do this (also method arguments), we need 'JoinPoints' in our 'advice-method'
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method : " + methodSignature);

        // Display the method arguments :

        // get args
//        Object[] args = joinPoint.getArgs();
//
//        // run a loop to print all those args
//        System.out.println("\nPrinting the arguments ....");
//        for (var tempArg : args)   {
//            System.out.println(tempArg);
//            if (tempArg instanceof Account) {
//                // downcast and print Account specific stuff
//                Account account = (Account) tempArg;
//                System.out.println("The Account : " + account.getName());
//                System.out.println("Account level : " + account.getLevel());
//            }
//        }
    }
}