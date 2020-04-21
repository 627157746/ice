package com.zhb.ice.common.security.service;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/17 16:05
 */
public class IceClientDetailsService extends JdbcClientDetailsService {

    public IceClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * @Description //TODO 重写原生方法支持redis缓存
     * @Date 2020/4/17 16:06
     **/
    @Override
    @SneakyThrows
    @Cacheable(value = "client", key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }
}
