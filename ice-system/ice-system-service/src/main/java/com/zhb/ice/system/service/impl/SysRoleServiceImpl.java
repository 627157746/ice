package com.zhb.ice.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.system.api.entity.SysRole;
import com.zhb.ice.system.mapper.SysRoleMapper;
import com.zhb.ice.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 21:11
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public List<SysRole> findRolesByUserId(int userId) {
        return baseMapper.listRolesByUserId(userId);
    }
}
