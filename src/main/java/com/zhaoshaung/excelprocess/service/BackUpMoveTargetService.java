package com.zhaoshaung.excelprocess.service;

import com.zhaoshaung.excelprocess.model.BackUpMoveTarget;

/**
 * @author zhangzhaoshuang
 * @date 2019-06-27
 * @time 21:53
 * @description
 */
public interface BackUpMoveTargetService {

    /**
     * 储存一条数据
     *
     * @param backUpMoveTarget 每天更新back up move 的target
     * @return boolean是否成功
     */
    boolean saveBackUpMoveTarget(BackUpMoveTarget backUpMoveTarget);

}
