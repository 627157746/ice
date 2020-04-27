package com.zhb.ice.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author zhb
 * @Description TODO 启动类
 * @Date 2020/4/17 14:37
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.zhb.ice")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
