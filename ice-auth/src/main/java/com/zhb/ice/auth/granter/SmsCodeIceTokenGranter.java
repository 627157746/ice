package com.zhb.ice.auth.granter;

import com.zhb.ice.common.security.service.IceUser;
import com.zhb.ice.common.security.service.IceUserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

import static com.zhb.ice.common.core.constant.SecurityConstants.SMS_GRANT_TYPE;


/**
 * @Author zhb
 * @Description TODO 手机短信登录
 * @Date 2020/4/23 17:00
 */
public class SmsCodeIceTokenGranter extends AbstractIceTokenGranter {

    private final IceUserDetailsService iceUserDetailsService;

    public SmsCodeIceTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, IceUserDetailsService iceUserDetailsService) {
        super(tokenServices, clientDetailsService, requestFactory, SMS_GRANT_TYPE);
        this.iceUserDetailsService = iceUserDetailsService;
    }

    @Override
    protected IceUser getIceUser(Map<String, String> parameters) {

        String mobile = parameters.get("phone");
        String smsCode = parameters.get("smsCode");
        return iceUserDetailsService.loadUserByPhoneAndSmsCode(mobile, smsCode);
    }
}
