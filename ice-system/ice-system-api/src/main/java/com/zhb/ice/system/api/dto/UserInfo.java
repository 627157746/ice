package com.zhb.ice.system.api.dto;

import com.zhb.ice.system.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author zhb
 * @Description TODO 用户信息对象
 * @Date 2020/4/20 21:08
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
    private List<Integer> roleIds;
}
