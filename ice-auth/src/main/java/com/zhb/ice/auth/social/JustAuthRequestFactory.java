package com.zhb.ice.auth.social;

import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import lombok.RequiredArgsConstructor;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.request.AuthQqRequest;
import me.zhyd.oauth.request.AuthRequest;

/**
 * @Author zhb
 * @Description TODO 根据第三方登录类型获取authRequest
 * @Date 2020/4/25 17:23
 */

@RequiredArgsConstructor
public class JustAuthRequestFactory {

    private final JustAuthProperties properties;


    public AuthRequest get(AuthDefaultSource source) {
        AuthConfig config = properties.getType().get(source.getName().toLowerCase());
        switch (source) {
            case QQ:
                return new AuthQqRequest(config);
            default:
                throw new BaseException(Status.NOT_SUPPORT_AUTH_TYPE);
        }
    }
}
