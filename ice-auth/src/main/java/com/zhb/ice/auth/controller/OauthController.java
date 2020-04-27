package com.zhb.ice.auth.controller;

import com.zhb.ice.auth.util.Oauth2AccessTokenUtil;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.core.util.SmsUtil;
import com.zhb.ice.system.api.dto.SmsLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/23 11:16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OauthController {

    private final TokenEndpoint tokenEndpoint;

    private final ConsumerTokenServices consumerTokenServices;

    private final SmsUtil smsUtil;

    /**
     * @Description //TODO 登录接口
     * @Date 2020/4/23 16:36
     **/
    @PostMapping("/token")
    public R<Map<String, Object>> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {

        return R.ofSuccess(Oauth2AccessTokenUtil.custom(tokenEndpoint.postAccessToken(principal, parameters).getBody()));
    }

    /**
     * @Description //TODO 注销接口
     * @Date 2020/4/23 16:37
     **/
    @GetMapping("/logout")
    public R logout(@RequestParam("access_token") String accessToken) {

        if (consumerTokenServices.revokeToken(accessToken)) {
            return R.ofSuccess();
        }
        return R.ofError();
    }

    @GetMapping("/send")
    public R send(@Validated SmsLogin smsLogin) {

        smsUtil.sendSmsCode(smsLogin.getPhone());
        return R.ofSuccess();
    }

}
