package com.zhb.ice.common.security.component;

import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import com.zhb.ice.common.security.annotation.Ignore;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @Author zhb
 * @Description TODO 不要拦截的接口
 * @Date 2020/4/22 17:09
 */
@Slf4j
@Configuration
@Component
@ConfigurationProperties(prefix = "security.oauth2.ignore")
public class PermitAllUrlProperties implements InitializingBean, ApplicationContextAware {

    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");
    private ApplicationContext applicationContext;

    @Getter
    @Setter
    private List<String> urls = new ArrayList<>();

    @Override
    @SneakyThrows
    public void afterPropertiesSet(){

        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            // 获取方法上边的注解 替代path variable 为 *
            Ignore method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Ignore.class);
            Optional.ofNullable(method)
                    .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                            .forEach(url -> urls.add(ReUtil.replaceAll(url, PATTERN, StringPool.ASTERISK))));

            // 获取类上边的注解, 替代path variable 为 *
            Ignore controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Ignore.class);
            Optional.ofNullable(controller)
                    .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                            .forEach(url -> urls.add(ReUtil.replaceAll(url, PATTERN, StringPool.ASTERISK))));
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }
}
