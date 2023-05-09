package com.njfu.edu.utils;

import com.mchange.v2.cfg.PropertiesConfig;
import com.mysql.cj.log.Log;
import com.mysql.cj.log.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Properties;
import java.util.logging.Logger;

//aop环绕使用
public class AopUtils {

    //log4j 日志
    Logger logger = Logger.getLogger(AopUtils.class.getName());

        public Object log(ProceedingJoinPoint pjp){
            Object rtValue = null;
            try{
                Object[] args = pjp.getArgs();//得到方法执行所需的参数
                logger.info("AopUtils开始记录日志了......前置");
                rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）
                logger.info("调用函数函数："+pjp.getSignature());
                logger.info("AopUtils开始记录日志了......后置");
                return rtValue;
            }catch (Throwable t){
                logger.info("AopUtils开始记录日志了......异常");
                throw new RuntimeException(t);
            }finally {
                logger.info("AopUtils开始记录日志了......最终");
            }
        }
}
