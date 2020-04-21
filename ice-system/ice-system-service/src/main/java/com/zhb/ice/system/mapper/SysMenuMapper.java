package com.zhb.ice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhb.ice.system.api.entity.SysMenu;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:41
 * @Version 1.0
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> listMenusByRoleId(int roleId);
}
