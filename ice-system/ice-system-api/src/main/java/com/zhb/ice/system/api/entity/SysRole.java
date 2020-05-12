package com.zhb.ice.system.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
public class SysRole implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String remarks;

    @TableField(fill = FieldFill.INSERT,updateStrategy = FieldStrategy.NEVER)
    private Long createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @TableField(value = "is_del")
    @TableLogic
    private Boolean del;
}
