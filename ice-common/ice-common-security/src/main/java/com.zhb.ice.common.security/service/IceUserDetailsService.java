package com.zhb.ice.common.security.service;

import com.zhb.ice.common.core.util.R;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.api.feign.RemoteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/17 16:57
 */
@Component
@RequiredArgsConstructor
public class IceUserDetailsService implements UserDetailsService {

    private final RemoteUserService remoteUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        R<UserInfo> userInfoR = remoteUserService.info(username);
        if (userInfoR == null || userInfoR.getCode() != 200) {
            throw new UsernameNotFoundException("username not fount");
        }
        UserInfo userInfo = userInfoR.getData();
        SysUser sysUser = userInfo.getSysUser();
        String[] roleNames = userInfo.getRoleNames();
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(roleNames);
        return IceUser.build(sysUser, authorities);
    }
}
