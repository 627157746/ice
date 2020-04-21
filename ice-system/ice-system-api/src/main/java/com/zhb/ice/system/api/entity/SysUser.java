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
 * @Date 2020/4/20 20:42
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String avatar;

    private String remark;

    private String deptId;

    private long createTime;

    private long updateTime;

    @TableField("is_lock_account")
    private boolean lockAccount;

    @TableField("is_del")
    private boolean del;
}
