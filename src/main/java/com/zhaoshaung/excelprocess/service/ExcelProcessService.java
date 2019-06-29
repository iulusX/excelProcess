package com.zhaoshaung.excelprocess.service;

import com.zhaoshaung.excelprocess.exception.ProcessExcellException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xiaoyunfeng
 * @date 2019-06-27
 * @time 22:01
 * @description
 */
public interface ExcelProcessService {

    /**
     * 处理excel文件，将数据存入数据库
     *
     * @param file 上传的文件
     * @return boolean处理是否成功
     */
    boolean excelProcess(MultipartFile file) throws ProcessExcellException, IOException;

}
