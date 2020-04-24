package com.zhb.ice.common.security.component;

import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zhb
 * @Description TODO 权限不足处理
 * @Date 2020/4/8 16:27
 */
@Component
public class IceAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtil.renderJson(response, Status.ACCESS_DENIED);
    }
}
