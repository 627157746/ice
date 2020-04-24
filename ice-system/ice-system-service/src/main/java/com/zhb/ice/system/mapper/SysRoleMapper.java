package com.zhb.ice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhb.ice.system.api.entity.SysRole;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 21:11
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> listRolesByUserId(int userId);
}
