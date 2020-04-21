package com.zhb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/21 19:00
 * @Version 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}
