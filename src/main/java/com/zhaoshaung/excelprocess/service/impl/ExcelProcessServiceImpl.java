package com.zhaoshaung.excelprocess.service.impl;

import com.google.common.collect.Maps;
import com.zhaoshaung.excelprocess.enums.ExcelEnum;
import com.zhaoshaung.excelprocess.exception.ProcessExcellException;
import com.zhaoshaung.excelprocess.model.BackUpMoveTarget;
import com.zhaoshaung.excelprocess.service.BackUpMoveTargetService;
import com.zhaoshaung.excelprocess.service.ExcelProcessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.zhaoshaung.excelprocess.exception.ProcessExcellException.*;
import static com.zhaoshaung.excelprocess.utils.Consts.*;

/**
 * @author xiaoyunfeng
 * @date 2019-06-27
 * @time 22:03
 * @description 解析excel文件，存入数据库
 */
@Slf4j
@Service
public class ExcelProcessServiceImpl implements ExcelProcessService {

    @Autowired
    private BackUpMoveTargetService backUpMoveTargetService;

    /**
     * 处理excel文件，将数据存入数据库
     *
     * @param file 上传的文件
     * @return boolean处理是否成功
     */
    @Override
    public boolean excelProcess(MultipartFile file) throws IOException, ProcessExcellException {

        if (Objects.isNull(file)) {
            log.info("ExcelProcessServiceImpl excelProcess 传入file为null");
            throw new ProcessExcellException(EMPTY_VALUE_EXCEPTION_CODE, "传入文件为空");
        }
        String fileName = file.getOriginalFilename();

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            log.info("ExcelProcessServiceImpl excelProcess 传入file的格式错误");
            throw new ProcessExcellException(FILE_FORMAT_EXCEPTION_CODE, "传入file的格式错误");
        }

        //判断是不是2003
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }


        //获取文件输入流
        InputStream is = file.getInputStream();
        Workbook wb;
        //获取ecxcel操作对象
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }

        //获取工作表的对象
        Sheet sheet = wb.getSheetAt(0);

        if (Objects.isNull(sheet)) {
            log.info("ExcelProcessServiceImpl excelProcess 工作表为空");
            throw new ProcessExcellException(PE_EXCEPTION_CODE, "工作表为空");
        }


        //遍历每行, 封装对象，存储
        //去掉第一行
        Map<Integer, String> valueMap = Maps.newHashMap();
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            BackUpMoveTarget backUpMoveTarget = processRow(row);
            backUpMoveTargetService.saveBackUpMoveTarget(backUpMoveTarget);
        }

        return true;
    }

    /**
     * 处理每行
     *
     * @param row 行
     * @return 储存对象
     * @throws ProcessExcellException 自定异常
     */
    private BackUpMoveTarget processRow(Row row) throws ProcessExcellException {

        //行号
        int rowIndex = row.getRowNum();
        Map<Integer, String> map = Maps.newHashMap();
        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            CellType cellType = ExcelEnum.getExcelEnum(cellNum).getCellType();

            //列号
            int columnIndex = cell.getColumnIndex();
            System.out.println(cell.getCellType());

            String value = StringUtils.EMPTY;
            switch (cellType) {
                case NUMERIC:
                    value = String.valueOf((long) cell.getNumericCellValue());
                    break;
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                default:
                    log.info("ExcelProcessServiceImpl processCells 含有未能识别的格式");

            }

            log.info("ExcelProcessServiceImpl process 第{}行，第{}列，值为 = {}", rowIndex + 1, columnIndex + 1, value);

            if (StringUtils.isBlank(value)) {
                throw new ProcessExcellException(PE_EXCEPTION_CODE, "导入失败, 第" + rowIndex + 1 + "行,第" + columnIndex + 1 + "行为空");
            }
            map.put(cell.getColumnIndex(), value);
        }

        map.entrySet().stream().forEach(System.out::println);

        BackUpMoveTarget backUpMoveTarget = BackUpMoveTarget.builder()
                .histDate(LocalDate.parse(map.get(HIST_DATE), DATE_FORMATTER))
                .tech(map.get(TECH))
                .stage(map.get(STAGE))
                .p1Target(map.get(P1_TARGET))
                .p2Target(map.get(P2_TARGET))
                .remark(map.get(REMARK))
                .build();
        log.info("ExcelProcessServiceImpl process 生成的对象为 = {}", backUpMoveTarget);
        return backUpMoveTarget;
    }

//    /**
//     * 处理每个单元格
//     * @param row 行
//     * @throws ProcessExcellException
//     */
//    private Map<Integer, String> processCells(Row row) throws ProcessExcellException {
//
//        //行号
//        int rowIndex = row.getRowNum();
//        Map<Integer, String> map = Maps.newHashMap();
//        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
//            Cell cell = row.getCell(cellNum);
//            CellType cellType = ExcelEnum.getExcelEnum(cellNum).getCellType();
//
//            //列号
//            int columnIndex = cell.getColumnIndex();
//            System.out.println(cell.getCellType());
//
//            String value = StringUtils.EMPTY;
//            switch (cellType) {
//                case NUMERIC:
//                    value = String.valueOf((long) cell.getNumericCellValue());
//                    break;
//                case STRING:
//                    value = cell.getStringCellValue();
//                    break;
//                default:
//                    log.info("ExcelProcessServiceImpl processCells 含有未能识别的格式");
//
//            }
//
//            log.info("ExcelProcessServiceImpl process 第{}行，第{}列，值为 = {}", rowIndex + 1, columnIndex + 1, value);
//
//            if (StringUtils.isBlank(value)) {
//                throw new ProcessExcellException(PE_EXCEPTION_CODE, "导入失败, 第" + rowIndex + 1 + "行,第" + columnIndex + 1 + "行为空");
//            }
//            map.put(cell.getColumnIndex(), Optional.ofNullable(value).orElse(StringUtils.EMPTY));
//        }
//        return map;
//    }


}
