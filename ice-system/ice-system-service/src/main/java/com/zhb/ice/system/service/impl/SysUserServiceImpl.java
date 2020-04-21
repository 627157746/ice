package com.zhb.ice.system.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.entity.SysMenu;
import com.zhb.ice.system.api.entity.SysRole;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.mapper.SysUserMapper;
import com.zhb.ice.system.service.SysMenuService;
import com.zhb.ice.system.service.SysRoleService;
import com.zhb.ice.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:41
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysRoleService sysRoleService;

    private final SysMenuService sysMenuService;

    @Override
    public UserInfo getUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        //设置角色列表  （ID）
        List<Integer> roleIds = sysRoleService.findRolesByUserId(sysUser.getId())
                .stream()
                .map(SysRole::getId)
                .collect(Collectors.toList());

        List<String> roleNames = sysRoleService.findRolesByUserId(sysUser.getId())
                .stream()
                .map(SysRole::getName)
                .collect(Collectors.toList());
        userInfo.setRoleNames(ArrayUtil.toArray(roleNames,String.class));

        //设置权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = sysMenuService.findMenuByRoleId(roleId)
                    .stream()
                    .filter(sysMenu -> StringUtils.isNotEmpty(sysMenu.getPermission()))
                    .map(SysMenu::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
        return userInfo;
    }

}
