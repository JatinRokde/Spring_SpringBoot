package com.jr.aopdemo.aspect;

import com.jr.aopdemo.Account;
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
    @Around("execution(* com.jr.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune (
        ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        // printout the method we are advising on
        String method = theProceedingJoinPoint.getSignature().toString();
        System.out.println("\n=====>>>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // execute the method
        Object result = theProceedingJoinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute the duration and display it
        long duration = end - begin;

        System.out.println("\n=====>>>>> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* com.jr.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        // print on which method we are adivising on
        String method = theJoinPoint.getSignature().toString();
        System.out.println("\n=====>>>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.jr.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toString();
        System.out.println("\n=====>>>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=====>>>>> The Exception is " + theExc);
    }

    // add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.jr.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
        // printout which method we are advising on
        String method = theJoinPoint.getSignature().toString();
        System.out.println("\n=====>>>>> Executing @AfterReturning on method: " + method);

        // printout the results of the method call
        System.out.println("\n=====>>>>> Result is: " + result);
        
        // post-processing/modifying the data
        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>>>>> Result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through accounts
        for(Account tempAccount : result) {
            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }
    }


    @Before("com.jr.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n=====>>>>> Executing @Before advice on addAccount()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display method arguments
        // get args
        Object[] args = theJoinPoint.getArgs();

        // loop through args
        for (Object tempArgs : args) {
            System.out.println(tempArgs);

            if(tempArgs instanceof Account) {
                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArgs;
                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account Level: " + theAccount.getLevel());
            }
        }

    }
}
