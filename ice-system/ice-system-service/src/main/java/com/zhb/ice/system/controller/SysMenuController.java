package com.zhb.ice.system.controller;

import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.core.validated.Insert;
import com.zhb.ice.common.core.validated.Update;
import com.zhb.ice.common.security.util.SecurityUtils;
import com.zhb.ice.system.api.entity.SysMenu;
import com.zhb.ice.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/6 14:47
 */
@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class SysMenuController {

    private final SysMenuService sysMenuService;

    /**
     * @Description //TODO 获取当前用户的菜单
     * @Date 2020/5/12 15:26
     **/
    @GetMapping
    public R getUserMenu(Integer parentId) {

        // 获取符合条件的菜单
        Set<SysMenu> all = new HashSet<>();
        SecurityUtils.getRoles()
                .forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
        return R.ofSuccess(sysMenuService.filterMenu(all, parentId));
    }

    /**
     * @Description //TODO 获取菜单树,isALL为是获取所有结果，否则获取菜单的不包含按钮
     * @Date 2020/5/13 9:43
     **/
    @GetMapping("/tree")
    public R getMenuTree(@RequestParam(defaultValue = "true") Boolean isAll, Integer parentId) {
        return R.ofSuccess(sysMenuService.menuTree(isAll, parentId));
    }

    /**
     * @Description //TODO 根据Id查询
     * @Date  2020/5/13 16:46
     **/
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return R.ofSuccess(sysMenuService.getById(id));
    }

    /**
     * @Description //TODO 添加菜单
     * @Date  2020/5/13 16:46
     **/
    @PostMapping
    @PreAuthorize("@ice.hasPermission('sys_menu_add')")
    public R add(@Validated(Insert.class) @RequestBody SysMenu sysMenu) {
        return R.ofSuccess(sysMenuService.save(sysMenu));
    }

    @PutMapping
    @PreAuthorize("@ice.hasPermission('sys_menu_edit')")
    public R updateById(@Validated(Update.class)@RequestBody SysMenu sysMenu){
        if (!sysMenuService.updateById(sysMenu)) {
            return R.ofStatus(Status.SAVE_ERROR);
        }
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 根据Id删除
     * @Date  2020/5/13 16:46
     **/
    @DeleteMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_menu_del')")
    public R delById(@PathVariable("id") Integer id) {
        sysMenuService.delById(id);
        return R.ofSuccess();
    }
}
