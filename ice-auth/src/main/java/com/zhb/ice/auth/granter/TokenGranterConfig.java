package com.zhb.ice.auth.granter;

import com.zhb.ice.common.security.service.IceClientDetailsService;
import com.zhb.ice.common.security.service.IceUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhb
 * @Description TODO 自定义TokenGranter配置
 * @Date 2020/4/24 9:51
 */
public class TokenGranterConfig {

    private final AuthorizationServerTokenServices tokenServices;
    private final AuthorizationCodeServices authorizationCodeServices;
    private final OAuth2RequestFactory requestFactory;
    private final IceClientDetailsService clientDetailsService;
    private final IceUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public TokenGranterConfig(AuthorizationServerTokenServices tokenServices, AuthorizationCodeServices authorizationCodeServices, OAuth2RequestFactory requestFactory, IceClientDetailsService clientDetailsService, IceUserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.tokenServices = tokenServices;
        this.authorizationCodeServices = authorizationCodeServices;
        this.requestFactory = requestFactory;
        this.clientDetailsService = clientDetailsService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public TokenGranter tokenGranter() {
        return new TokenGranter() {
            private CompositeTokenGranter delegate;

            @Override
            public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
                if (delegate == null) {
                    delegate = new CompositeTokenGranter(getDefaultTokenGranters());
                }
                return delegate.grant(grantType, tokenRequest);
            }
        };
    }

    private List<TokenGranter> getDefaultTokenGranters() {

        List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService,
                requestFactory));
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetailsService, requestFactory));
        ImplicitTokenGranter implicit = new ImplicitTokenGranter(tokenServices, clientDetailsService, requestFactory);
        tokenGranters.add(implicit);
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetailsService, requestFactory));
        //添加自定义的手机短信验证码登录
        tokenGranters.add(new SmsCodeIceTokenGranter(tokenServices, clientDetailsService, requestFactory, userDetailsService));
        if (authenticationManager != null) {
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices,
                    clientDetailsService, requestFactory));
        }
        return tokenGranters;
    }
}
