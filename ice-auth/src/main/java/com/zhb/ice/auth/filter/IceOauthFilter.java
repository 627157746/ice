package com.zhb.ice.auth.filter;

import cn.hutool.crypto.symmetric.AES;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.ResponseUtil;
import com.zhb.ice.common.security.service.IceClientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

import static com.zhb.ice.common.core.constant.SecurityConstants.OAUTH_TOKEN_URL;

/**
 * @Author zhb
 * @Description TODO 拦截登录请求验证客户端是否正确
 * @Date 2020/4/23 11:40
 */
@Component
@RequiredArgsConstructor
public class IceOauthFilter extends OncePerRequestFilter {

    private final IceClientDetailsService clientDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final AES aes;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //不是登录端点直接放行
        if (!request.getRequestURI().equals(OAUTH_TOKEN_URL)) {
            filterChain.doFilter(request, response);
            return;
        }
        //获取头部client数据，为空直接输出异常
        String[] clientArrays = isHasClient(request);
        if (clientArrays == null) {
            ResponseUtil.renderJson(response, Status.INVALID_CLIENT_PARAM);
            return;
        }

        //验证头部client数据
        handle(request, response, clientArrays, filterChain);

    }

    private void handle(HttpServletRequest request, HttpServletResponse response, String[] clientArrays, FilterChain filterChain) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //已认证直接放行
        if (authentication != null && authentication.isAuthenticated()) {
            filterChain.doFilter(request, response);
            return;
        }

        //未查询到client直接输出异常
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientArrays[0]);
        if (clientDetails == null) {
            ResponseUtil.renderJson(response, Status.INVALID_CLIENT_PARAM);
            return;
        }
        //client_secret不匹配直接输出异常
        if (!passwordEncoder.matches(clientArrays[1], clientDetails.getClientSecret())) {
            ResponseUtil.renderJson(response, Status.INVALID_CLIENT_PARAM);
            return;
        }

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(clientDetails.getClientId(), clientDetails.getClientSecret(), clientDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(token);

        filterChain.doFilter(request, response);
    }

    private String[] isHasClient(HttpServletRequest request) {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String[] clientArrays;
        if (header != null) {
            String basic = header.substring(0, 5);
            if (basic.toLowerCase().contains("basic")) {
                String tmp = header.substring(6);
                String defaultClientDetails = new String(Base64.getDecoder().decode(tmp));
                clientArrays = defaultClientDetails.split(":");
                if (clientArrays.length == 2) {
                    return clientArrays;
                }
            }
        }
        return null;
    }
}
