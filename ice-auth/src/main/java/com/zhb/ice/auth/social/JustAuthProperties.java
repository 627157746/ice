package com.zhb.ice.auth.social;

import lombok.Getter;
import lombok.Setter;
import me.zhyd.oauth.config.AuthConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhb
 * @Description TODO 第三方认证配置类
 * @Date 2020/4/25 16:13
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "justauth")
public class JustAuthProperties {

    private boolean enabled = false;

    private Map<String, AuthConfig> type = new HashMap<>();
}
