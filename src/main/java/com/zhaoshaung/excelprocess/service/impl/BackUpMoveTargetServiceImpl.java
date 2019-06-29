package com.zhaoshaung.excelprocess.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaoshaung.excelprocess.mapper.BackUpMoveTargetmapper;
import com.zhaoshaung.excelprocess.model.BackUpMoveTarget;
import com.zhaoshaung.excelprocess.service.BackUpMoveTargetService;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyunfeng
 * @date 2019-06-27
 * @time 21:54
 * @description
 */
@Service
public class BackUpMoveTargetServiceImpl extends ServiceImpl<BackUpMoveTargetmapper, BackUpMoveTarget> implements BackUpMoveTargetService {

    /**
     * 储存一条数据
     *
     * @param backUpMoveTarget 每天更新back up move 的target
     * @return boolean是否成功
     */
    @Override
    public boolean saveBackUpMoveTarget(BackUpMoveTarget backUpMoveTarget) {
        return save(backUpMoveTarget);
    }
}
