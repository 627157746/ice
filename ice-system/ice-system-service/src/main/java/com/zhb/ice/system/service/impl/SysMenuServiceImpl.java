package com.zhb.ice.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.system.api.entity.SysMenu;
import com.zhb.ice.system.mapper.SysMenuMapper;
import com.zhb.ice.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 21:11
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findMenuByRoleId(int roleId) {
        return sysMenuMapper.listMenusByRoleId(roleId);
    }
}
