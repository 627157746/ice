package com.zhb.ice.common.security.util;

import com.zhb.ice.common.security.service.IceUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/6 14:33
 */
@UtilityClass
public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public IceUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof IceUser) {
            return (IceUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public IceUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<Integer> getRoles() {
        Authentication authentication = getAuthentication();
        IceUser iceUser = (IceUser) authentication.getPrincipal();
        return iceUser.getRoleIds();
    }
}
