package com.zhaoshaung.excelprocess.utils;

import java.time.format.DateTimeFormatter;

/**
 * @author zhangzhaoshuang
 * @date 2019-06-27
 * @time 21:46
 * @description
 */
public class Consts {
    /**
     * 格式化日期
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 表格字段
     */
    //日期
    public static final int HIST_DATE = 0;
    //产品
    public static final int TECH = 1;
    //站点
    public static final int STAGE = 2;
    //p1的量
    public static final int P1_TARGET = 3;
    //p2的量
    public static final int P2_TARGET = 4;
    //注释
    public static final int REMARK = 5;

}
