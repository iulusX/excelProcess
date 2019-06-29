package com.zhaoshaung.excelprocess.Exception;

import lombok.Data;

import java.util.Objects;

/**
 * @author xiaoyunfeng
 * @date 2019-06-27
 * @time 22:29
 * @description
 */
public class ProcessExcellException extends Exception{

    private int code;
    private String msg;

    public ProcessExcellException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ProcessExcellException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
