package com.zhb.ice.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.zhb.ice.auth.service.JustAuthService;
import com.zhb.ice.auth.social.JustAuthProperties;
import com.zhb.ice.auth.social.JustAuthRequestFactory;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.common.core.util.R;
import lombok.RequiredArgsConstructor;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author zhb
 * @Description TODO 第三方认证接口
 * @Date 2020/4/24 14:44
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class JustAuthController {

    private final JustAuthProperties properties;

    private final JustAuthService justAuthService;

    private final JustAuthRequestFactory justAuthFactory;

    /**
     * @Description //TODO 返回第三方登录界面
     * @Date 2020/4/25 18:45
     **/
    @RequestMapping("/login/{authType}")
    public void renderAuth(@PathVariable String authType, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest(authType);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    /**
     * @Description //TODO 回调接口
     * @Date 2020/4/25 18:45
     **/
    @RequestMapping("/{authType}/callback")
    public R<Map<String, Object>> login(@PathVariable String authType, AuthCallback callback) {
        return R.ofSuccess(justAuthService.login(callback, getAuthRequest(authType)));
    }

    private void isEnabled() {
        if (properties.isEnabled()) {
            return;
        }
        throw new BaseException(Status.CLOSE_AUTH_TYPE);
    }

    private AuthRequest getAuthRequest(String authType) {
        isEnabled();
        if (StrUtil.isNotBlank(authType)) {
            return justAuthFactory.get(AuthDefaultSource.valueOf(authType.toUpperCase()));
        } else {
            throw new BaseException(Status.NOT_SUPPORT_AUTH_TYPE);
        }
    }
}
