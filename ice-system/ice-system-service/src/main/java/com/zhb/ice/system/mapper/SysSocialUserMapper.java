package com.zhb.ice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhb.ice.system.api.entity.SysSocialUser;
import com.zhb.ice.system.api.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:41
 */
public interface SysSocialUserMapper extends BaseMapper<SysSocialUser> {

    SysUser selectSysUserByOpenId(@Param("openId") String openId);
}
