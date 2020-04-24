package com.zhb.ice.auth.controller;

import com.zhb.ice.auth.service.RestAuthService;
import com.zhb.ice.common.core.util.R;
import lombok.RequiredArgsConstructor;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthQqRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/24 14:44
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class RestAuthController {

    private final RestAuthService restAuthService;


    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback")
    public R<Map<String, Object>> login(AuthCallback callback) {
        return R.ofSuccess(restAuthService.login(callback, getAuthRequest()));
    }

    private AuthRequest getAuthRequest() {
        return new AuthQqRequest(AuthConfig.builder()
                .clientId("101766255")
                .clientSecret("d3961c2bf0a28dfd79d62406a5d312ee")
                .redirectUri("http://www.icemq.com/auth/qq")
                .build());
    }
}
