package com.zhb.ice.system.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/15 10:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict_item")
public class SysDictItem {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String value;

    private String label;

    private String type;

    private Integer sort;

    private String remarks;

    @TableField(fill = FieldFill.INSERT,updateStrategy = FieldStrategy.NEVER)
    private Long createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    private Integer dictId;

    @TableField(value = "is_del")
    @TableLogic
    private Boolean del;
}
