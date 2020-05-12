package com.zhb.ice.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhb.ice.system.api.vo.MenuTree;
import com.zhb.ice.system.api.entity.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 21:10
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findMenuByRoleId(int roleId);

    List<MenuTree> filterMenu(Set<SysMenu> all, Integer parentId);

    List<MenuTree> menuTree(Integer parentId);
}
