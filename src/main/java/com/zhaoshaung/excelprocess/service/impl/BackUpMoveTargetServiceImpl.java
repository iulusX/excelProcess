package com.zhaoshaung.excelprocess.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaoshaung.excelprocess.mapper.BackUpMoveTargetMapper;
import com.zhaoshaung.excelprocess.model.BackUpMoveTarget;
import com.zhaoshaung.excelprocess.service.BackUpMoveTargetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhangzhaoshuang
 * @date 2019-06-27
 * @time 21:54
 * @description
 */
@Slf4j
@Service
public class BackUpMoveTargetServiceImpl extends ServiceImpl<BackUpMoveTargetMapper, BackUpMoveTarget> implements BackUpMoveTargetService {

    /**
     * 储存一条数据
     *
     * @param backUpMoveTarget 每天更新back up move 的target
     * @return boolean是否成功
     */
    @Override
    public boolean saveBackUpMoveTarget(BackUpMoveTarget backUpMoveTarget) {
        boolean isSuccess = save(backUpMoveTarget);
        log.info("BackUpMoveTargetServiceImpl saveBackUpMoveTarget 保存成功啦～");
        return isSuccess;
    }
}
