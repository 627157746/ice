package com.zhb.ice.gateway.config;

import com.zhb.ice.gateway.handler.HystrixFallbackHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @Author zhb
 * @Description TODO 降级配置
 * @Date 2020/4/26 11:47
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class RouterFunctionConfiguration {

    private final HystrixFallbackHandler hystrixFallbackHandler;

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.path("/fallback")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), hystrixFallbackHandler);

    }

}
