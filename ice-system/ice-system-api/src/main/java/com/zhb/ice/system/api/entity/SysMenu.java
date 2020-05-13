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
 * @Date 2020/4/20 20:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class SysMenu implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = {Update.class},message = "菜单名称不能为空！")
    private Integer id;

    @NotEmpty(groups = {Insert.class},message = "菜单名称不能为空！")
    private String name;

    private String permission;

    private String path;

    @NotNull(groups = {Insert.class},message = "父菜单不能为空！")
    private Integer parentId;

    private String icon;

    private String component;

    @NotNull(groups = {Insert.class},message = "排序不能为空！")
    private Integer sort;

    @TableField("is_no_cache")
    @NotNull(groups = {Insert.class},message = "缓存不能为空！")
    private Boolean noCache;

    @TableField("is_hidden")
    @NotNull(groups = {Insert.class},message = "隐藏不能为空！")
    private Boolean hidden;

    @NotEmpty(groups = {Insert.class},message = "类型不能为空！")
    private String type;

    @TableField(fill = FieldFill.INSERT,updateStrategy = FieldStrategy.NEVER)
    private Long createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @TableField(value = "is_del")
    @TableLogic
    private Boolean del;


}
