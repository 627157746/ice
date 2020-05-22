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
 * @Date 2020/5/15 10:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict")
public class SysDict {

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = {Update.class},message = "主键不能为空！")
    private Integer id;

    @NotEmpty(groups = {Insert.class},message = "名称不能为空！")
    private String name;

    @NotEmpty(groups = {Insert.class},message = "类型不能为空！")
    private String type;

    @NotEmpty(groups = {Insert.class},message = "备注能为空！")
    private String remarks;

    @NotNull(groups = {Insert.class},message = "字典类型不能为空！")
    private Boolean isSystem;

    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private Long createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @TableField(value = "is_del")
    @TableLogic
    private Boolean del;
}
