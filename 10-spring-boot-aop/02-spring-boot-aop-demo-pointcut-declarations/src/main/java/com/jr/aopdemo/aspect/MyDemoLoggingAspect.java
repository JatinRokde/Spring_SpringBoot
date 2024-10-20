package com.jr.aopdemo.aspect;

import com.jr.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
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
