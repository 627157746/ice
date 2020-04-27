package com.zhb.ice.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.zhb.ice.common.core.constant.SecurityConstants.FROM_K;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

/**
 * @Author zhb
 * @Description TODO 全局请求过滤
 * @Date 2020/4/27 9:51
 */
@Component
public class IceRequestGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 清洗请求头中from 参数
        ServerHttpRequest request = exchange.getRequest().mutate()
                .headers(httpHeaders -> httpHeaders.remove(FROM_K))
                .build();

        // 2. 重写StripPrefix
        addOriginalRequestUrl(exchange, request.getURI());
        String rawPath = request.getURI().getRawPath();
        String newPath = "/" + Arrays.stream(StringUtils.tokenizeToStringArray(rawPath, "/"))
                .skip(1L).collect(Collectors.joining("/"));
        ServerHttpRequest newRequest = request.mutate()
                .path(newPath)
                .build();
        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, newRequest.getURI());

        return chain.filter(exchange.mutate()
                .request(newRequest.mutate()
                        .build()).build());
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
