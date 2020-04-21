package com.zhb.ice.system.controller;

import com.zhb.ice.common.core.util.R;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/19 19:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public R test(Authentication authentication) {
        return R.ofSuccess(authentication);
    }

}
