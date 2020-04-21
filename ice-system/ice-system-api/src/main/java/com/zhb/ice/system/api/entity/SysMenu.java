package com.zhb.ice.system.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:44
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String name;

    private String permission;

    private String path;

    private String parentId;

    private String icon;

    private String component;

    private int sort;

    @TableField("is_keep_alive")
    private boolean keepAlive;

    private String type;

    private long createTime;

    private long updateTime;

    @TableField("is_del")
    private boolean del;


}
