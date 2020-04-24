package com.zhb.ice.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.system.api.entity.SysSocialUser;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.mapper.SysSocialUserMapper;
import com.zhb.ice.system.service.SysSocialUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/24 16:24
 */
@Service
@RequiredArgsConstructor
public class SysSocialUserServiceImpl extends ServiceImpl<SysSocialUserMapper, SysSocialUser> implements SysSocialUserService {

    private final SysSocialUserMapper sysSocialUserMapper;

    @Override
    public SysUser getSysUserByOpenId(String openId) {
        return sysSocialUserMapper.selectSysUserByOpenId(openId);
    }
}
