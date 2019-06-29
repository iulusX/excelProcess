package com.zhaoshaung.excelprocess.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhaoshaung.excelprocess.utils.LocalDateTimeJsonDeserializer;
import com.zhaoshaung.excelprocess.utils.LocalDateTimeJsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xiaoyunfeng
 * @date 2019-06-27
 * @time 21:35
 * @description 每天更新back up move 的target
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("mfg_tb_backup_move_target")
public class BackUpMoveTarget extends Model<BackUpMoveTarget> {
    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 日期
     */
    private LocalDateTime histDate;
    /**
     * 产品
     */
    private String tech;
    /**
     * 站点
     */
    private String stage;
    /**
     * p1的量
     */
    private String p1Target;
    /**
     * p2的量
     */
    private String p2Target;
    /**
     * 注释
     */
    private String remark;
    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeJsonSerializable.class)
    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonSerialize(using = LocalDateTimeJsonSerializable.class)
    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
