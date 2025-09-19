package com.example.dpchatapi.config.AopConfig.anno;



import com.example.dpchatapi.config.AopConfig.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 公共字段自动填充
 */
//运行时生效
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutoFijll {
    OperationType value();
}
