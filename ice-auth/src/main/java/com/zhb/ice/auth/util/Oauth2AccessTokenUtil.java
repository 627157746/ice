package com.zhb.ice.auth.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author zhb
 * @Description TODO Oauth2AccessToken获取工具类
 * @Date 2020/4/24 16:56
 */
@UtilityClass
public class Oauth2AccessTokenUtil {

    public Map<String, Object> custom(OAuth2AccessToken accessToken) {

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        Map<String, Object> data = new LinkedHashMap<>(token.getAdditionalInformation());
        data.put("accessToken", token.getValue());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        return data;
    }
}
