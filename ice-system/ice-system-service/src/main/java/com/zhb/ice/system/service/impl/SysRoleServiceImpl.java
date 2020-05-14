package com.zhb.ice.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.system.api.entity.SysMenu;
import com.zhb.ice.system.api.entity.SysRole;
import com.zhb.ice.system.api.entity.SysRoleMenu;
import com.zhb.ice.system.api.entity.SysUserRole;
import com.zhb.ice.system.mapper.SysRoleMapper;
import com.zhb.ice.system.service.SysMenuService;
import com.zhb.ice.system.service.SysRoleMenuService;
import com.zhb.ice.system.service.SysRoleService;
import com.zhb.ice.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 21:11
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysMenuService sysMenuService;

    private final SysUserRoleService sysUserRoleService;

    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysRole> findRolesByUserId(int userId) {
        return baseMapper.listRolesByUserId(userId);
    }

    @Override
    public List<Integer> getMenuIdsById(Integer id) {

        return sysMenuService.findMenuByRoleId(id)
                .stream()
                .map(SysMenu::getId)
                .collect(Collectors.toList());
    }

    @Override
    public void delById(Integer id) {
        SysUserRole existUserRole = sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getRoleId, id),false);
        if (existUserRole != null) {
            throw new BaseException(Status.BAD_REQUEST.getCode(), "该角色含有关联的用户！");
        }
        if (!this.removeById(id)) {
            throw new BaseException(Status.REMOVE_ERROR);
        }
    }

    @Override
    @Transactional
    public void updateRoleMenuById(Integer id, List<Integer> menuIds) {
        sysRoleMenuService.remove(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getRoleId, id));
        List<SysRoleMenu> sysRoleMenuList = menuIds.stream()
                .map(menuId -> new SysRoleMenu(id, menuId))
                .collect(Collectors.toList());
        sysRoleMenuService.saveBatch(sysRoleMenuList);
    }
}
