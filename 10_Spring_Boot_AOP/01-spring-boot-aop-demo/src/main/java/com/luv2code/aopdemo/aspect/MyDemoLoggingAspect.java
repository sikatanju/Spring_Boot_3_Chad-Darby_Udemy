package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    // This is where we add all of our related advices for logging.

    // Let's start with a @Before Advice


//    @Before("execution(public void add*())")
//    public void beforeAddAccountAdvice()    { // Our method can have any name
//         System.out.println("\n------>>> Executing @Before Advice on addAccount() ;)");
//
//    }

    // @Before("execution(* add*(com.luv2code.aopdemo.Account, .. ))")
    // -- Here in above statement, second parameter '..' means it could be any number of parameter from here own of any type

    // @Before("execution(* add*(com.luv2code.aopdemo.Account, '*' ))")
    // -- Here in above statement, second parameter '*' it could be any parameter but only one of any type.

//    @Before("execution(* add*(com.luv2code.aopdemo.Account, .. ))")
//    public void beforeActualAccount()   {
//        System.out.println("----> Executing before this statement for Actual Account class");
//    }
//
//    @Before("execution(* add*(..))")
//    public void useAnyParameter()   {
//        System.out.println("----> Executing before 'use any number of arguments of any type :)");
//    }

    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void forDAO()   {
        System.out.println("----> Executing before any method under \"dao\" package :)");
    }
}
// @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")

//    @Before("execution(public * add*())")
//    public void beforeAnyReturnType()    { // Our method can have any name
//        System.out.println("\n------>>> Executing @Before Advice on any return type when name is add*() ;)");
//    }
