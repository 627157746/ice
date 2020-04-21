package com.zhb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/21 19:22
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients()
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
