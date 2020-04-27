package com.zhb.ice.common.security.service;

import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.core.util.SmsUtil;
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
import java.util.List;

import static com.zhb.ice.common.core.constant.SecurityConstants.*;

/**
 * @Author zhb
 * @Description TODO IceUserDetailsService
 * @Date 2020/4/17 16:57
 */
@Component
@RequiredArgsConstructor
public class IceUserDetailsService implements UserDetailsService {

    private final RemoteUserService remoteUserService;
    private final SmsUtil smsUtil;


    /**
     * @Description //TODO 账号密码登录
     * @Date  2020/4/26 9:28
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        R<UserInfo> userInfoR = remoteUserService.info(username, USERNAME, FROM_V);
        return getUserDetails(userInfoR);
    }

    /**
     * @Description //TODO 手机短信登录
     * @Date  2020/4/26 9:28
     **/
    public IceUser loadUserByPhoneAndSmsCode(String phone, String smsCode) throws UsernameNotFoundException {

        if (!smsUtil.checkSmsCode(phone, smsCode)) {
            throw new BaseException(Status.SMS_CODE_ERROR);
        }

        R<UserInfo> userInfoR = remoteUserService.info(phone, PHONE, FROM_V);
        return getUserDetails(userInfoR);
    }


    private IceUser getUserDetails(R<UserInfo> userInfoR) {
        switch (userInfoR.getCode()) {
            case 500:
                throw new BaseException(Status.ERROR);
            case 405:
                throw new BaseException(Status.USERNAME_PASSWORD_ERROR);
            default:
                break;
        }
        UserInfo userInfo = userInfoR.getData();
        SysUser sysUser = userInfo.getSysUser();
        List<String> roleNames = userInfo.getRoleNames();
        String[] permissions = userInfo.getPermissions();
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(permissions);
        return IceUser.build(sysUser, roleNames, authorities);
    }
}
