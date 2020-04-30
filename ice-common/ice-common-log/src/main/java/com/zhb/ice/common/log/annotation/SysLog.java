package com.zhb.ice.common.log.annotation;

import java.lang.annotation.*;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/30 16:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 描述
     */
    String value();
}
