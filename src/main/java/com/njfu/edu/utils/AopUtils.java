package com.njfu.edu.utils;

import org.aspectj.lang.ProceedingJoinPoint;

//aop环绕使用
public class AopUtils {

        public Object log(ProceedingJoinPoint pjp){
            Object rtValue = null;
            try{
                Object[] args = pjp.getArgs();//得到方法执行所需的参数
                System.out.println("AopUtils开始记录日志了......前置");
                rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）
                System.out.println("函数："+pjp.getSignature());
                System.out.println("AopUtils开始记录日志了......后置");
                return rtValue;
            }catch (Throwable t){
                System.out.println("AopUtils开始记录日志了......异常");
                throw new RuntimeException(t);
            }finally {
                System.out.println("AopUtils开始记录日志了......最终");
            }
        }
}
