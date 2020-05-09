package com.zhb.ice.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.entity.SysSocialUser;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.api.vo.SysUserVO;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:40
 */
public interface SysUserService extends IService<SysUser> {

    UserInfo getUserInfo(SysUser sysUser);

    void register(SysUser sysUser, SysSocialUser sysSocialUser);

    IPage pageByQuery(Page page, SysUser sysUser);

    SysUserVO getById(Integer id);
}
