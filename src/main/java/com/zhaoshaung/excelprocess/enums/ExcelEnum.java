package com.zhaoshaung.excelprocess.enums;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangzhaoshuang
 * @date 2019-06-30
 * @time 13:02
 * @description
 */
public enum ExcelEnum implements Serializable {


    HIST_DATE(0, "日期", CellType.NUMERIC),
    TECH(1, "产品", CellType.STRING),
    STAGE(2, "站点", CellType.STRING),
    P1_TARGET(3, "p1的量", CellType.NUMERIC),
    P2_TARGET(4, "p1的量", CellType.NUMERIC),
    REMARK(5, "注释", CellType.STRING);

    private int code;
    private String desc;
    private CellType cellType;

    ExcelEnum(int code, String desc, CellType cellType) {
        this.code = code;
        this.desc = desc;
        this.cellType = cellType;
    }

    /**
     * 获取code
     *
     * @return int
     */
    public int getCode() {
        return this.code;
    }

    public CellType getCellType(){
        return this.cellType;
    }

    /**
     * 获取enum
     *
     * @param code code
     * @return ExcelEnum
     */
    public static ExcelEnum getExcelEnum(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }

        return Stream.of(ExcelEnum.values()).collect(Collectors.toMap(ExcelEnum::getCode, excelEnum -> excelEnum))
                .get(code);
    }


}
