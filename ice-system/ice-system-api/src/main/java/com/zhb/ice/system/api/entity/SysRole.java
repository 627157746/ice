package com.zhb.ice.system.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.zhb.ice.common.core.validated.Insert;
import com.zhb.ice.common.core.validated.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(groups = {Update.class},message = "主键不能为空！")
    private Integer id;

    @NotEmpty(groups = {Insert.class},message = "角色名不能为空！")
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
