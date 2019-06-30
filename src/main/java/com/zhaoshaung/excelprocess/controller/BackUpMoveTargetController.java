package com.zhaoshaung.excelprocess.controller;

import com.zhaoshaung.excelprocess.exception.ProcessExcellException;
import com.zhaoshaung.excelprocess.model.Resp;
import com.zhaoshaung.excelprocess.service.ExcelProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zhangzhaoshuang
 * @date 2019-06-27
 * @time 21:58
 * @description
 */
@RestController
@RequestMapping("/zs/web/target")
public class BackUpMoveTargetController {


    @Autowired
    private ExcelProcessService excelProcessService;

    /**
     * 储存一条数据
     *
     * @param
     * @return boolean是否成功
     */
    @PostMapping("/save")
    Resp saveBackUpMoveTarget(@RequestParam("file") MultipartFile file) throws ProcessExcellException, IOException {
        excelProcessService.excelProcess(file);
        return Resp.Ok();
    }

}
