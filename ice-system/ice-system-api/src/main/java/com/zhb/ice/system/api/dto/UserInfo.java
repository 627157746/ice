package com.zhb.ice.system.api.dto;

import com.zhb.ice.system.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 21:08
 * @Version 1.0
 */
@Data
public class UserInfo implements Serializable {

    /**
     * 用户基本信息
     */
    private SysUser sysUser;
    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private String[] roleNames;
}
