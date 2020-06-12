package com.example.studyDemo.configuration.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfig {

    @Pointcut("execution(* com.example.studyDemo.service.impl.TestForAOPServiceImpl.*(..))")
    private void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before方法执行");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("before方法执行");
    }


}
