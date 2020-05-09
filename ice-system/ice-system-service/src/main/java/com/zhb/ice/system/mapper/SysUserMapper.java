package com.zhb.ice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.api.vo.SysUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:41
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<List<SysUserVO>> pageByQuery(Page page, @Param("query") SysUser sysUser);

    SysUserVO selectById(Integer id);
}
