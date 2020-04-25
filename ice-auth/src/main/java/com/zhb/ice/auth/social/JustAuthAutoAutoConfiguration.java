package com.zhb.ice.auth.social;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhb
 * @Description TODO 第三方认证配置类
 * @Date 2020/4/25 18:22
 */
@Configuration
@EnableConfigurationProperties(JustAuthProperties.class)
public class JustAuthAutoAutoConfiguration {

    @Bean
//    @ConditionalOnProperty(prefix = "justauth", value = "enabled", havingValue = "true", matchIfMissing = true)
    public JustAuthRequestFactory authRequestFactory(JustAuthProperties properties) {
        return new JustAuthRequestFactory(properties);
    }
}
