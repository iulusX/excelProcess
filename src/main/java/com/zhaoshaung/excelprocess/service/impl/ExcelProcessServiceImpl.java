package com.zhaoshaung.excelprocess.service.impl;

import com.zhaoshaung.excelprocess.Exception.ProcessExcellException;
import com.zhaoshaung.excelprocess.model.BackUpMoveTarget;
import com.zhaoshaung.excelprocess.service.BackUpMoveTargetService;
import com.zhaoshaung.excelprocess.service.ExcelProcessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

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

        if (Objects.isNull(file)){
            log.info("ExcelProcessServiceImpl excelProcess 传入file为null");
            throw new ProcessExcellException(50001,"传入文件为空");
        }
        String fileName = file.getOriginalFilename();

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            log.info("ExcelProcessServiceImpl excelProcess 传入file的格式错误");
            throw new ProcessExcellException(50002,"传入file的格式错误");
        }

        //判断是不是2003
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }



        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        boolean notNull = false;
        if(sheet!=null){
            notNull = true;
        }

        //遍历每行，判断格式
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);

            if (row == null){
                continue;
            }
            if( row.getCell(0).getCellType() !=1){
                throw new ProcessExcellException(50003, "导入失败(第"+(r+1)+"行,姓名请设为文本格式)");
            }
            String name = row.getCell(0).getStringCellValue();

            if(name == null || name.isEmpty()){
                throw new ProcessExcellException(50003, "导入失败(第"+(r+1)+"行,姓名未填写)");
            }

            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            String phone = row.getCell(1).getStringCellValue();
            if(phone==null || phone.isEmpty()){
                throw new ProcessExcellException(50003, "导入失败(第"+(r+1)+"行,电话未填写)");
            }
            String add = row.getCell(2).getStringCellValue();
            if(add==null){
                throw new ProcessExcellException(50003, "导入失败(第"+(r+1)+"行,不存在此单位或单位未填写)");
            }
            Date date;
            if(row.getCell(3).getCellType() !=0){
                throw new ProcessExcellException(50003, "导入失败(第"+(r+1)+"行,入职日期格式不正确或未填写)");
            }else{
                row.getCell(3).getDateCellValue();
            }

            String des = row.getCell(4).getStringCellValue();

            //TODO 将数据放入model，将model放入list。


        }

        return notNull;



        //return backUpMoveTargetService.saveBackUpMoveTarget(new BackUpMoveTarget());
    }
}
