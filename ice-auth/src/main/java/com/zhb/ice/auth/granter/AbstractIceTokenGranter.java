package com.zhb.ice.auth.granter;

import com.zhb.ice.common.security.service.IceUser;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Map;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/23 16:52
 */
public abstract class AbstractIceTokenGranter extends AbstractTokenGranter {

    private final OAuth2RequestFactory requestFactory;

    protected AbstractIceTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.requestFactory = requestFactory;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = tokenRequest.getRequestParameters();
        IceUser iceUser = getIceUser(parameters);
        if (iceUser == null) {
            throw new InvalidGrantException("无法获取用户信息");
        }
        OAuth2Request storedOAuth2Request = this.requestFactory.createOAuth2Request(client, tokenRequest);
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(iceUser, null, iceUser.getAuthorities());
        authentication.setDetails(iceUser);
        return new OAuth2Authentication(storedOAuth2Request, authentication);
    }


    protected abstract IceUser getIceUser(Map<String, String> parameters);

}
