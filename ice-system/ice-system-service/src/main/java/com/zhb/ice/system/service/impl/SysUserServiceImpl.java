package com.zhb.ice.system.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.entity.SysMenu;
import com.zhb.ice.system.api.entity.SysRole;
import com.zhb.ice.system.api.entity.SysSocialUser;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.api.vo.SysUserVO;
import com.zhb.ice.system.mapper.SysUserMapper;
import com.zhb.ice.system.service.SysMenuService;
import com.zhb.ice.system.service.SysRoleService;
import com.zhb.ice.system.service.SysSocialUserService;
import com.zhb.ice.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:41
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysRoleService sysRoleService;

    private final SysMenuService sysMenuService;

    private final SysSocialUserService sysSocialUserService;

    @Override
    public UserInfo getUserInfo(SysUser sysUser) {

        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        //设置角色列表
        List<Integer> roleIds = sysRoleService.findRolesByUserId(sysUser.getId())
                .stream()
                .map(SysRole::getId)
                .collect(Collectors.toList());

        userInfo.setRoleIds(roleIds);

        //设置权限列表
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

    @Override
    @Transactional
    public void register(SysUser sysUser, SysSocialUser sysSocialUser) {

        this.save(sysUser);
        sysSocialUser.setUid(sysUser.getId());
        sysSocialUserService.save(sysSocialUser);
    }

    @Override
    public IPage pageByQuery(Page page, SysUser sysUser) {
        return this.baseMapper.pageByQuery(page,sysUser);
    }

    @Override
    public SysUserVO getById(Integer id) {
        return this.baseMapper.selectById(id);
    }
}
