/*
 *
 *  *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  *  <p>
 *  *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *  <p>
 *  * https://www.gnu.org/licenses/lgpl.html
 *  *  <p>
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.zhb.ice.auth.config;

import com.zhb.ice.auth.filter.IceOauthFilter;
import com.zhb.ice.auth.granter.TokenGranterConfig;
import com.zhb.ice.common.security.service.IceClientDetailsService;
import com.zhb.ice.common.security.service.IceUser;
import com.zhb.ice.common.security.service.IceUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

import static com.zhb.ice.common.core.constant.SecurityConstants.CLIENT_KEY_PREFIX;

/**
 * @Author zhb
 * @Description TODO 认证服务器配置
 * @Date 2020/4/23 9:06
 */
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final IceClientDetailsService clientDetailsService;
    private final IceUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final RedisConnectionFactory redisConnectionFactory;
    private final PasswordEncoder passwordEncoder;
    private final IceOauthFilter iceOauthFilter;

    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {

        clients.withClientDetails(clientDetailsService)
                .jdbc().passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {

        oauthServer
//                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
        oauthServer.addTokenEndpointAuthenticationFilter(iceOauthFilter);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        TokenGranter tokenGranter = new TokenGranterConfig(endpoints.getTokenServices(),
                endpoints.getAuthorizationCodeServices(),
                endpoints.getOAuth2RequestFactory(),
                clientDetailsService,
                userDetailsService,
                authenticationManager)
                .tokenGranter();

        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancer())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
//                .exceptionTranslator(new IceWebResponseExceptionTranslator())
                .reuseRefreshTokens(false)
                .tokenGranter(tokenGranter);
    }

    @Bean
    public TokenStore tokenStore() {

        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(CLIENT_KEY_PREFIX);
        return tokenStore;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(9);
            IceUser iceUser = (IceUser) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put("id", iceUser.getId());
            additionalInfo.put("username", iceUser.getUsername());
            additionalInfo.put("nickname", iceUser.getNickname());
            additionalInfo.put("phone", iceUser.getPhone());
            additionalInfo.put("avatar", iceUser.getAvatar());
            additionalInfo.put("roleIds", iceUser.getRoleIds());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}
