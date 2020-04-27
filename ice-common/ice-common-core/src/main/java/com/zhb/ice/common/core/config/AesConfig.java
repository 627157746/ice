package com.zhb.ice.common.core.config;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhb
 * @Description TODO AES配置
 * @Date 2020/4/26 14:13
 */
@Configuration
public class AesConfig {

    @Value("${aes.password}")
    private String aesPassword;

    @Bean
    public AES aes() {
        return new AES(Mode.ECB, Padding.PKCS5Padding,
                aesPassword.getBytes());
    }
}
