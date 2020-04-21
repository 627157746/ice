package com.zhb.ice.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhb.ice.system.api.entity.SysMenu;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 21:10
 * @Version 1.0
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> findMenuByRoleId(int roleId);
}
