package com.example.dpchatapi.config.AopConfig.aop;



import com.example.dpchatapi.config.AopConfig.anno.AutoFijll;

import com.example.dpchatapi.config.AopConfig.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect //切面类
@Slf4j
public class LogAspect {

    @Around("@annotation(com.example.dpchatapi.config.AopConfig.anno.Log)")
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        log.info("开始执行方法......");

        // 调用原始目标方法运行
        Object result = joinPoint.proceed();

        // 记录结束时间并计算执行时长
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info("执行完成，耗时: {}ms", executionTime);

        return result;
    }

    @Around("@annotation(com.example.dpchatapi.config.AopConfig.anno.AutoFijll)")
    public Object record1(ProceedingJoinPoint joinPoint) throws Throwable{
        //获取方法签名对象（Spring的MethodSignature）
        org.aspectj.lang.reflect.MethodSignature signature = (org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature();
        //获取方法对象
        java.lang.reflect.Method method = signature.getMethod();
        //获取方法上的注解对象
        AutoFijll autoFijll = method.getAnnotation(AutoFijll.class);
        //获取数据库操作类型
        OperationType operationType = autoFijll != null ? autoFijll.value() : null;

        // 调用原始目标方法运行
        Object result = joinPoint.proceed();

        if (operationType == OperationType.INSERT) {
            log.info("这是INSERT方法......");
        }else if (operationType == OperationType.UPDATE) {
            log.info("这是UPDATE方法......");
        }

        return result;
    }
}
