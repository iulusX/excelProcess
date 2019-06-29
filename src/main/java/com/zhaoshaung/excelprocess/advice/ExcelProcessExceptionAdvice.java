package com.zhaoshaung.excelprocess.advice;

import com.zhaoshaung.excelprocess.Exception.ProcessExcellException;
import com.zhaoshaung.excelprocess.model.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xiaoyunfeng
 * @date 2019-06-29
 * @time 11:48
 * @description
 */
@Slf4j
@RestControllerAdvice("com.zhaoshaung.excelprocess.controller")
public class ExcelProcessExceptionAdvice {

    /**
     * excel处理异常handler
     *
     * @param e ProcessExcellException
     * @return Resp
     */
    @ExceptionHandler(ProcessExcellException.class)
    public Resp processExcellExceptionHandler(ProcessExcellException e) {
        log.info("ExcelProcessExceptionAdvice processExcellExceptionHandler 出错了！e = {}", e);
        return new Resp(e.getCode(), e.getMsg());
    }



    /**
     * excel处理异常handler
     *
     * @param e ProcessExcellException
     * @return Resp
     */
    @ExceptionHandler(Exception.class)
    public Resp ExceptionHandler(Exception e) {
        log.info("ExcelProcessExceptionAdvice ExceptionHandler 出错了！e = {}", e);
        return Resp.Fail();
    }

}
