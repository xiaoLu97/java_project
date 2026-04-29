package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@EnableAspectJAutoProxy
public class LoggerAspect {
    /**
     * 参数日志
     */
//    @Before("execution(public int com.aop.CalImpl.add(..))")
    @Before("execution(public int com.aop.CalImpl.*(..))")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(name + "方法的参数是" + args);
    }

//    @AfterReturning(value = "execution(public int com.aop.CalImpl.add(..))", returning = "res")
    @AfterReturning(value = "execution(public int com.aop.CalImpl.*(..))", returning = "res")
    public void afterReturn(JoinPoint joinPoint, Object res){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法的执行结果是" + res);
    }
}
