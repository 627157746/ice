package com.zhb.ice.system.api.dto;

import com.zhb.ice.system.api.entity.SysSocialUser;
import com.zhb.ice.system.api.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhb
 * @Description TODO 封装第三方用户注册类
 * @Date 2020/4/24 17:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserDto {

    /**
     * 用户
     */
    private SysUser sysUser;

    /**
     * 第三方用户
     */
    private SysSocialUser sysSocialUser;
}
