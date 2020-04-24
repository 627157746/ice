package com.zhb.ice.common.security.component;

import cn.hutool.core.util.StrUtil;
import com.zhb.ice.common.core.constant.SecurityConstants;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.common.security.annotation.Ignore;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhb
 * @Description TODO Ignore切面，主要判断是否内部调用
 * @Date 2020/4/22 17:13
 */
@Aspect
@Slf4j
@RequiredArgsConstructor
public class IceIgnoreAspect implements Ordered {

    private final HttpServletRequest request;

    @SneakyThrows
    @Around("@annotation(ignore)")
    public Object around(ProceedingJoinPoint point, Ignore ignore) {

        String header = request.getHeader(SecurityConstants.FROM_K);
        if (ignore.value() && !StrUtil.equals(SecurityConstants.FROM_V, header)) {
            log.warn("访问接口 {} 没有权限", point.getSignature().getName());
            throw new BaseException(Status.ACCESS_DENIED);
        }
        return point.proceed();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
