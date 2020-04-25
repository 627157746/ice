package com.zhb.ice.auth.service;

import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthRequest;

import java.util.Map;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/24 15:45
 */
public interface JustAuthService {

    Map<String, Object> login(AuthCallback callback, AuthRequest authRequest);
}
