package com.zhb.ice.common.core.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhb
 * @Description TODO redis工具类
 * @Date 2020/4/24 10:47
 */
@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * @Description //TODO 存入一个无期限的数据
     * @Date 2020/4/24 10:57
     **/
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * @Description //TODO 存入一个带期限的数据
     * @Date 2020/4/24 10:58
     **/
    public void set(String key, Object value, long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    /**
     * @Description //TODO 根据指定key获取数据
     * @Date  2020/4/24 11:03
     **/
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * @Description //TODO 删除指定key的数据
     * @Date  2020/4/24 11:03
     **/
    public void delete(String key){
        redisTemplate.delete(key);
    }


}
