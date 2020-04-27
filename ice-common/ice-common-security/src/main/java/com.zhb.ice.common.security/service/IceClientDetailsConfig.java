package com.zhb.ice.common.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @Author zhb
 * @Description TODO clientDetails配置
 * @Date 2020/4/23 15:03
 */
@Configuration
@RequiredArgsConstructor
public class IceClientDetailsConfig {

    private final DataSource dataSource;

    @Bean
    public IceClientDetailsService iceClientDetailsService(){
        return new IceClientDetailsService(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
