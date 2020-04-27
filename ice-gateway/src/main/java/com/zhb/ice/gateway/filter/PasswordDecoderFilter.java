package com.zhb.ice.gateway.handler;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.zhb.ice.common.core.constant.SecurityConstants;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

/**
 * @Author zhb
 * @Description TODO 密码解密
 * @Date 2020/4/26 13:52
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory {

    private static final String PASSWORD = "password";

    private final AES aes;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.OAUTH_TOKEN_URL)) {
                return chain.filter(exchange);
            }

            URI uri = exchange.getRequest().getURI();
            String queryParam = uri.getRawQuery();
            Map<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, CharsetUtil.UTF_8);
            String password = paramMap.get(PASSWORD);
            if (StrUtil.isNotBlank(password)) {
                try {
                    password = aes.decryptStr(password);
                } catch (Exception e) {
                    throw new BaseException(Status.USERNAME_PASSWORD_ERROR);
                }
                paramMap.put(PASSWORD, password.trim());
            }

            URI newUri = UriComponentsBuilder.fromUri(uri)
                    .replaceQuery(HttpUtil.toParams(paramMap))
                    .build(true)
                    .toUri();

            ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(newRequest).build());
        };
    }
}
