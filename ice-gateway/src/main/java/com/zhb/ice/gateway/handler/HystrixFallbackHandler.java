package com.zhb.ice.gateway.handler;

import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

/**
 * @Author zhb
 * @Description TODO 降级控制
 * @Date 2020/4/26 11:31
 */
@Slf4j
@Component
public class HystrixFallbackHandler implements HandlerFunction<ServerResponse> {
    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);

        originalUris.ifPresent(originalUri -> log.error("网关执行请求:{}失败,hystrix服务降级处理", originalUri));

        return ServerResponse.status(Status.SUCCESS.getCode())
                .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(R.ofError()));
    }
}
