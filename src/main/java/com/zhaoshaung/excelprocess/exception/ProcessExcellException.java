package com.zhaoshaung.excelprocess.exception;

/**
 * @author xiaoyunfeng
 * @date 2019-06-27
 * @time 22:29
 * @description
 */
public class ProcessExcellException extends Exception{

    private int code;
    private String msg;

    /**
     * 错误码
     */
    public static final int PE_EXCEPTION_CODE = 50003;
    public static final int EMPTY_VALUE_EXCEPTION_CODE = 50001;
    public static final int FILE_FORMAT_EXCEPTION_CODE = 50002;

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
