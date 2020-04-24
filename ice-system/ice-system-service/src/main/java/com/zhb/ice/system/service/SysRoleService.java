package com.zhb.ice.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhb.ice.system.api.entity.SysRole;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 21:10
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRole> findRolesByUserId(int userId);

}
