package com.zhb.ice.auth.config;

import cn.hutool.core.collection.ListUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/17 16:57
 */
@Component
public class IceUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        Collection<GrantedAuthority> authorities = ListUtil.list(false, new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User("admin", new BCryptPasswordEncoder().encode("123456"), authorities);
    }
}
