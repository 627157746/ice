package com.zhb.ice.system.controller;

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
     * @Date  2020/5/9 16:22
     **/
    @GetMapping
    public R<List<SysRole>> list(){
        return R.ofSuccess(sysRoleService.list());
    }
}
