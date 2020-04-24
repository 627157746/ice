package com.zhb.ice.common.security.service;

import com.zhb.ice.system.api.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Author zhb
 * @Description TODO IceUser
 * @Date 2020/4/21 17:37
 * @Version 1.0
 */
@Data
public class IceUser implements UserDetails {

    private int id;

    private final String username;

    private String password;

    private String nickname;

    private String phone;

    private String avatar;

    private boolean lockAccount;

    private final List<String> roleNames;

    private final Collection<? extends GrantedAuthority> authorities;

    public static IceUser build(SysUser sysUser,List<String> roleNames, Collection<? extends GrantedAuthority> authorities) {
        return new IceUser(sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(), sysUser.getNickname(), sysUser.getPhone(), sysUser.getAvatar(), sysUser.isLockAccount(),roleNames, authorities);
    }

    private IceUser(int id, String username, String password, String nickname, String phone, String avatar, boolean lockAccount,List<String> roleNames, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.avatar = avatar;
        this.lockAccount = lockAccount;
        this.roleNames = roleNames;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !lockAccount;
    }
}
