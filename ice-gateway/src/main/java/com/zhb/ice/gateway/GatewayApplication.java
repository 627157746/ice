package com.zhb.ice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 9:59
 * @Version 1.0
 */
@SpringCloudApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
