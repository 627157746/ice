package com.zhb.ice.system.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.zhb.ice.common.core.validated.Insert;
import com.zhb.ice.common.core.validated.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotNull(groups = {Update.class},message = "主键不能为空！")
    private Integer id;

    @NotEmpty(groups = {Insert.class},message = "名称不能为空！")
    private String name;

    @NotNull(groups = {Insert.class},message = "排序不能为空！")
    private Integer sort;

    @TableField(fill = FieldFill.INSERT,updateStrategy = FieldStrategy.NEVER)
    private Long createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @NotNull(groups = {Insert.class},message = "父部门不能为空！")
    private Integer parentId;

    @TableField(value = "is_del")
    @TableLogic
    private Boolean del;
}
