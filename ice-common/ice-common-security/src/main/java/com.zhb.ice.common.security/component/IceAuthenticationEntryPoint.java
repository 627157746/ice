package com.zhb.ice.common.security.component;

import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.ResponseUtil;
import lombok.SneakyThrows;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author zhb
 * @Description TODO 未登录处理
 * @Date 2020/4/8 16:21
 */
@Component
public class IceAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException){
        String message = authException.getMessage();
//        if (message.startsWith(ACCESS_TOKEN_EXPIRED) || message.startsWith(INVALID_ACCESS_TOKEN) || message.startsWith(CANNOT_CONVERT_ACCESS_TOKEN)) {
//            ResponseUtil.renderJson(response, Status.TOKEN_INVALID);
//        } else {
//            ResponseUtil.renderJson(response, Status.UNAUTHORIZED);
//        }
        if (message.startsWith("Full")){
            ResponseUtil.renderJson(response, Status.UNAUTHORIZED);
        }else {
            ResponseUtil.renderJson(response, Status.TOKEN_INVALID);
        }
    }
}
