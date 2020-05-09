package com.zhb.ice.system.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/8 16:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dept")
public class SysDept {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Long updateTime;

    private Integer parentId;

    @TableField("is_del")
    @TableLogic
    private Boolean del;
}
