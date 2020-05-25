package com.zhb.ice.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.core.validated.Insert;
import com.zhb.ice.common.core.validated.Update;
import com.zhb.ice.system.api.entity.SysRole;
import com.zhb.ice.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("@ice.hasPermission('sys_role_query')")
    public R<List<SysRole>> list() {
        return R.ofSuccess(sysRoleService.list());
    }

    /**
     * @Description //TODO 分页查询角色列表
     * @Date  2020/5/13 17:18
     **/
    @GetMapping("/page")
    @PreAuthorize("@ice.hasPermission('sys_role_query')")
    public R pageByQuery(Page<SysRole> page, String name) {
        LambdaQueryWrapper<SysRole> wrapper = Wrappers.<SysRole>lambdaQuery()
                .like(StrUtil.isNotBlank(name), SysRole::getName, name)
                .or()
                .like(StrUtil.isNotBlank(name), SysRole::getRemarks, name);
        return R.ofSuccess(sysRoleService.page(page, wrapper));
    }

    /**
     * @Description //TODO 根据id查询角色信息
     * @Date  2020/5/14 15:13
     **/
    @GetMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_role_query')")
    public R getById(@PathVariable("id")Integer id){
        return R.ofSuccess(sysRoleService.getById(id));
    }

    /**
     * @Description //TODO 根据id查询角色所拥有的菜单权限
     * @Date  2020/5/14 15:14
     **/
    @GetMapping("/menus/{id}")
    @PreAuthorize("@ice.hasPermission('sys_role_query')")
    public R getMenuIdsById(@PathVariable("id")Integer id){
        return R.ofSuccess(sysRoleService.getMenuIdsById(id));
    }

    /**
     * @Description //TODO 添加角色
     * @Date  2020/5/14 15:14
     **/
    @PostMapping
    @PreAuthorize("@ice.hasPermission('sys_role_add')")
    public R add(@Validated(Insert.class) @RequestBody SysRole sysRole){
        if (!sysRoleService.save(sysRole)) {
            return R.ofStatus(Status.SAVE_ERROR);
        }
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 修改角色
     * @Date  2020/5/14 15:14
     **/
    @PutMapping
    @PreAuthorize("@ice.hasPermission('sys_role_edit')")
    public R update(@Validated(Update.class) @RequestBody SysRole sysRole){
        if (!sysRoleService.updateById(sysRole)) {
            return R.ofStatus(Status.UPDATE_ERROR);
        }
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 根据id修改角色的菜单权限
     * @Date  2020/5/14 15:15
     **/
    @PutMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_role_edit')")
    public R updateRoleMenuById(@PathVariable("id")Integer id ,@RequestBody List<Integer> menuIds){
        sysRoleService.updateRoleMenuById(id,menuIds);
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 根据id删除角色
     * @Date  2020/5/14 15:15
     **/
    @DeleteMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_role_del')")
    public R delById(@PathVariable("id") Integer id){
        sysRoleService.delById(id);
        return R.ofSuccess();
    }
}
