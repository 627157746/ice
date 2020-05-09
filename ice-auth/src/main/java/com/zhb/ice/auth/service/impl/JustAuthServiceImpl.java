package com.zhb.ice.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.zhb.ice.auth.service.JustAuthService;
import com.zhb.ice.auth.util.Oauth2AccessTokenUtil;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.system.api.dto.SysSocialUserDTO;
import com.zhb.ice.system.api.entity.SysSocialUser;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.api.feign.RemoteUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.zhb.ice.common.core.constant.SecurityConstants.*;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/24 15:45
 */
@Transactional
@RequiredArgsConstructor
@Service
public class JustAuthServiceImpl implements JustAuthService {

    private final TokenEndpoint tokenEndpoint;

    private final RemoteUserService remoteUserService;

    private final PasswordEncoder passwordEncoder;

    @Override
    @SneakyThrows
    public Map<String, Object> login(AuthCallback callback, AuthRequest authRequest) {

        //获取回调的数据
        AuthResponse response = authRequest.login(callback);
        if (response.getCode() == AuthResponseStatus.SUCCESS.getCode()) {
            AuthUser data = (AuthUser) response.getData();
            AuthToken authToken = data.getToken();
            OAuth2AccessToken oAuth2AccessToken = null;
            //查询openId是否存在
            R<SysUser> sysUserR = remoteUserService.getSysUserByOpenId(authToken.getOpenId(), FROM_V);
            if (sysUserR.getCode() != Status.ERROR.getCode()) {
                SysUser sysUser;
                //不存在直接生成新用户
                if (sysUserR.getCode() == Status.NOT_FOUND_DATA.getCode()) {
                    sysUser = SysUser.builder()
                            .nickname(data.getNickname())
                            .username(RandomUtil.randomString(10))
                            .avatar(data.getAvatar())
                            .password(passwordEncoder.encode(DEFAULT_REGISTER_PASSWORD))
                            .deptId(10)
                            .build();
                    SysSocialUser sysSocialUser = SysSocialUser.builder()
                            .accessToken(authToken.getAccessToken())
                            .openId(authToken.getOpenId())
                            .refreshToken(authToken.getRefreshToken())
                            .type(data.getSource())
                            .build();
                    remoteUserService.register(new SysSocialUserDTO(sysUser, sysSocialUser), FROM_V);
                } else {
                    sysUser = sysUserR.getData();
                }
                //手动密码模式授权
                UsernamePasswordAuthenticationToken clientToken = new UsernamePasswordAuthenticationToken(DEFAULT_CLIENT_ID, DEFAULT_CLIENT_SECRET, null);
                SecurityContextHolder.getContext().setAuthentication(clientToken);
                Map<String, String> parameters = new HashMap<>(5);
                parameters.put("username", sysUser.getUsername());
                parameters.put("password", DEFAULT_REGISTER_PASSWORD);
                parameters.put("grant_type", "password");

                //获取accessToken
                oAuth2AccessToken = tokenEndpoint.postAccessToken(clientToken, parameters).getBody();
            }
            return Oauth2AccessTokenUtil.custom(oAuth2AccessToken);
        }

        throw new BaseException(Status.ERROR);

    }
}
