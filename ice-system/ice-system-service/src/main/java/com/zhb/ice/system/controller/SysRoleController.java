package com.zhb.ice.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.system.api.entity.SysRole;
import com.zhb.ice.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/9 16:12
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    /**
     * @Description //TODO 获取所有角色
     * @Date 2020/5/9 16:22
     **/
    @GetMapping
    public R<List<SysRole>> list() {
        return R.ofSuccess(sysRoleService.list());
    }

    /**
     * @Description //TODO 分页查询角色列表
     * @Date  2020/5/13 17:18
     **/
    @GetMapping("/page")
    public R pageByQuery(Page<SysRole> page, String name) {
        LambdaQueryWrapper<SysRole> wrapper = Wrappers.<SysRole>lambdaQuery()
                .like(StrUtil.isNotBlank(name), SysRole::getName, name)
                .or()
                .like(StrUtil.isNotBlank(name), SysRole::getRemarks, name);
        return R.ofSuccess(sysRoleService.page(page, wrapper));
    }
}
