package com.zhb.ice.gateway.exception;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @Author zhb
 * @Description TODO 异常捕获
 * @Date 2020/4/26 14:47
 */
@Slf4j
public class IceErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {
    /**
     * Create a new {@code DefaultErrorWebExceptionHandler} instance.
     *
     * @param errorAttributes    the error attributes
     * @param resourceProperties the resources configuration properties
     * @param errorProperties    the error configuration properties
     * @param applicationContext the current application context
     */
    public IceErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * @Description //TODO 判断并返回异常响应
     * @Date  2020/4/27 9:55
     **/
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {

        Throwable error = super.getError(request);
        log.error("网关异常:{},异常类:{}", error.getMessage(), error.getClass());
        if (error instanceof BaseException) {
            return BeanUtil.beanToMap(R.ofException((BaseException) error));
        }
        if (error instanceof FlowException||error instanceof ParamFlowException){
            return BeanUtil.beanToMap(R.ofStatus(Status.TOO_MANY_REQUEST));
        }

        Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
        int httpStatus = super.getHttpStatus(errorAttributes);
        if (httpStatus == Status.REQUEST_NOT_FOUND.getCode()) {
            return BeanUtil.beanToMap(R.ofStatus(Status.REQUEST_NOT_FOUND));
        }
        return BeanUtil.beanToMap(R.ofError());
    }

    /**
     * @Description //TODO 重写渲染异常响应，统一返回200状态码，使用自定义状态码
     * @Date  2020/4/27 9:59
     **/
    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        boolean includeStackTrace = isIncludeStackTrace(request, MediaType.ALL);
        Map<String, Object> error = getErrorAttributes(request, includeStackTrace);
        return ServerResponse.status(Status.SUCCESS.getCode()).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(error));
    }

    /**
     * @Description //TODO 指定响应处理方法为JSON处理的方法
     * @Date  2020/4/27 9:54
     **/
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }
}
