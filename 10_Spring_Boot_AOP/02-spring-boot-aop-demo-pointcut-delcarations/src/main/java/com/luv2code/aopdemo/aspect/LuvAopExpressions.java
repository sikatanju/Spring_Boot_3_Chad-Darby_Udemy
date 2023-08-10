package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // If we only have pointcuts then @Aspect is optional
// Aspect is mandatory only when we have advice here.
public class LuvAopExpressions {
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void forDaoPackage()   {}

    // Declaring pointcut expression for get() method.
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    public void getter()  {}

    // Pointcut expression for set() method.
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    public void setter()  {}

    // Now combining pointcut expression.
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter()   {}
}
