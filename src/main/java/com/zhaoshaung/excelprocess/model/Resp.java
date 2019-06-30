package com.zhaoshaung.excelprocess.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangzhaoshuang
 * @date 2019-06-29
 * @time 11:52
 * @description 前端返回对象
 */
@Data
public class Resp implements Serializable {
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回对象
     */
    private Object obj;


    public Resp(int code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }
    public Resp(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public Resp(){
    }

    public static Resp Ok(){
        return new Resp(20000, "成功");
    }
    public static Resp Fail(){
        return new Resp(50000, "失败");
    }


}
