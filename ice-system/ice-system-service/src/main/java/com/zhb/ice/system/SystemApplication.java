package com.zhb.ice.system;

import com.zhb.ice.common.security.annotation.EnableIceResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/19 19:33
 */
@SpringCloudApplication
@EnableIceResourceServer
@EnableFeignClients
@MapperScan("com.zhb.ice.system.mapper")
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
