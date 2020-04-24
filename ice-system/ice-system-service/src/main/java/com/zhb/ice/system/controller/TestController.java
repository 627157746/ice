package com.zhb.ice.system.controller;

import com.zhb.ice.common.core.util.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/19 19:34
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    @PreAuthorize("@ice.hasPermission('sys_user_add')")
    public R test() {
        return R.ofSuccess();
    }

}
