package com.zhb.ice.system.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.system.api.dto.SysUserDto;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.entity.*;
import com.zhb.ice.system.api.vo.SysUserVO;
import com.zhb.ice.system.mapper.SysUserMapper;
import com.zhb.ice.system.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.zhb.ice.common.core.constant.SecurityConstants.*;

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

    private final SysUserRoleService sysUserRoleService;

    private final PasswordEncoder passwordEncoder;

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

        if (!this.save(sysUser)) {
            throw new BaseException(Status.SAVE_ERROR);
        }

        sysSocialUser.setUid(sysUser.getId());
        if (!sysSocialUserService.save(sysSocialUser)) {
            throw new BaseException(Status.SAVE_ERROR);
        }

        if (!sysUserRoleService.save(new SysUserRole(sysUser.getId(), DEFAULT_REGISTER_ROLE_ID))) {
            throw new BaseException(Status.SAVE_ERROR);
        }
    }

    @Override
    @Transactional
    public void register(SysUserDto sysUserDto) {

        LambdaQueryWrapper<SysUser> wrapper = Wrappers.<SysUser>query()
                .lambda()
                .eq(SysUser::getUsername, sysUserDto.getUsername())
                .or()
                .eq(SysUser::getPhone, sysUserDto.getPhone());
        SysUser sysUser = this.getOne(wrapper);
        //查询用户名或者手机号是被被注册
        if (sysUser != null) {
            throw new BaseException(Status.USERNAME_OR_PHONE_EXIST);
        }
        //密码加密
        sysUserDto.setPassword(passwordEncoder.encode(sysUserDto.getPassword()));
        if (sysUserDto.getDeptId() == null) {
            sysUserDto.setDeptId(DEFAULT_DEPT_ID);
        }
        if (sysUserDto.getLockAccount() == null) {
            sysUserDto.setLockAccount(false);
        }
        if (!this.save(sysUserDto)) {
            throw new BaseException(Status.SAVE_ERROR);
        }

        List<Integer> roleIds = sysUserDto.getRoleIds();
        //角色为空则注册为普通用户
        if (roleIds == null) {
            sysUserRoleService.save(new SysUserRole(sysUserDto.getId(), DEFAULT_REGISTER_ROLE_ID));
        } else {
            List<SysUserRole> sysUserRoles = ListUtil.list(false);
            roleIds.forEach(roleId -> sysUserRoles.add(new SysUserRole(sysUserDto.getId(), roleId)));
            if (!sysUserRoleService.saveBatch(sysUserRoles)) {
                throw new BaseException(Status.SAVE_ERROR);
            }
        }
    }

    @Override
    public IPage pageByQuery(Page page, SysUser sysUser) {
        return this.baseMapper.pageByQuery(page, sysUser);
    }

    @Override
    public SysUserVO getById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void delById(Integer id) {
        if (id.equals(ADMIN_USER_ID)) {
            throw new BaseException(500,"管理员账户不允许被删除");
        }
        if (!this.removeById(id)) {
            throw new BaseException(Status.REMOVE_ERROR);
        }

        //有可能用户没绑定第三方用户，则不判断
        sysSocialUserService.remove(
                Wrappers
                        .<SysSocialUser>query()
                        .lambda()
                        .eq(SysSocialUser::getUid, id));

    }

    @Override
    public void delByIds(List<Integer> ids) {
        if (ids.contains(ADMIN_USER_ID)){
            throw new BaseException(500,"管理员账户不允许被删除");
        }
        if (!this.removeByIds(ids)) {
            throw new BaseException(Status.REMOVE_ERROR);
        }

        //有可能用户没绑定第三方用户，则不判断
        sysSocialUserService.remove(Wrappers
                .<SysSocialUser>query()
                .lambda()
                .in(SysSocialUser::getUid, ids));
    }

    @Override
    @Transactional
    public void update(SysUserDto sysUserDto) {
        if (!this.updateById(sysUserDto)) {
            throw new BaseException(Status.UPDATE_ERROR);
        }

        List<Integer> roleIds = sysUserDto.getRoleIds();

        List<SysUserRole> sysUserRoles = ListUtil.list(false);
        roleIds.forEach(roleId -> sysUserRoles.add(new SysUserRole(sysUserDto.getId(), roleId)));

        sysUserRoleService.remove(Wrappers
                .<SysUserRole>query()
                .lambda()
                .eq(SysUserRole::getUserId, sysUserDto.getId()));

        sysUserRoleService.saveBatch(sysUserRoles);
    }
}
