package com.zhb.ice.common.security.component;

import com.zhb.ice.common.security.service.IceUser;
import com.zhb.ice.system.api.entity.SysUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhb
 * @Description TODO 自定义解析
 * @Date 2020/5/6 16:33
 */
public class IceUserAuthenticationConverter implements UserAuthenticationConverter {
    private static final String N_A = "N/A";
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(USERNAME, userAuthentication.getName());
        if (userAuthentication.getAuthorities() != null && !userAuthentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(userAuthentication.getAuthorities()));
        }
        return response;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);

            String username = (String) map.get("username");
            String nickname = (String) map.get("nickname");
            Integer id = (Integer) map.get("id");
            String avatar = (String) map.get("avatar");
            String phone = (String) map.get("phone");
            List<Integer> roles = (List<Integer>) map.get("roleIds");
            IceUser user = IceUser.build(SysUser.builder()
                    .id(id)
                    .username(username)
                    .nickname(nickname)
                    .password(N_A)
                    .lockAccount(false)
                    .phone(phone)
                    .avatar(avatar)
                    .build(),roles,authorities);
            return new UsernamePasswordAuthenticationToken(user, N_A, authorities);
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }
}
