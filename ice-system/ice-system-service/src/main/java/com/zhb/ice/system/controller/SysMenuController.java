package com.zhb.ice.system.controller;

import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.security.util.SecurityUtils;
import com.zhb.ice.system.api.entity.SysMenu;
import com.zhb.ice.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public R getUserMenu(Integer parentId) {

        // 获取符合条件的菜单
        Set<SysMenu> all = new HashSet<>();
        SecurityUtils.getRoles()
                .forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
        return R.ofSuccess(sysMenuService.filterMenu(all, parentId));
    }
}
