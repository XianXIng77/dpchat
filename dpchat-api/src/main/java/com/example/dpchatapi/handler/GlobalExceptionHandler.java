package com.example.dpchatapi.handler;


import com.example.dpchatapi.exception.BaseException;
import com.example.dpchatapi.result.Result;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
    /**
     * 捕获其他所有异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleOtherExceptions(Exception ex) {
        log.error("未知异常：{}", ex.getMessage(), ex);
        return Result.error("系统异常，请稍后重试或联系管理员");
    }
}
