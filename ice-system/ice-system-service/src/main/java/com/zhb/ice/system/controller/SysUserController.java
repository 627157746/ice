package com.zhb.ice.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:40
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("/info/{username}")
    public R info(@PathVariable String username) {
        SysUser user = sysUserService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return R.ofError();
        }
        return R.ofSuccess(sysUserService.getUserInfo(user));
    }
}
