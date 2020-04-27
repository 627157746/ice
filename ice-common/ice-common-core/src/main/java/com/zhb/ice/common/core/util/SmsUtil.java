package com.zhb.ice.common.core.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.zhb.ice.common.core.constant.CommonConstants.SMS_KEY_PREFIX;
import static com.zhb.ice.common.core.constant.RegexConstants.PHONE_REGEX;

/**
 * @Author zhb
 * @Description TODO 手机短信工具
 * @Date 2020/4/24 10:28
 */
@Component
@RequiredArgsConstructor
public class SmsUtil {

    private final RedisUtil redisUtil;

    /**
     * @Description //TODO 发送手机验证码，并且把验证码存入缓存时间5分钟
     * @Date 2020/4/24 11:11
     **/
    public void sendSmsCode(String phone) {
        //可自行加入第三方sdk
        String smsCode = RandomUtil.randomNumbers(6);
        redisUtil.set(SMS_KEY_PREFIX + phone, smsCode, 5, TimeUnit.MINUTES);
    }

    /**
     * @Description //TODO 检验验证码是否正确
     * @Date 2020/4/24 11:14
     **/
    public boolean checkSmsCode(String phone, String inputSmsCode) {

        if (StrUtil.isNotBlank(phone) && StrUtil.isNotBlank(inputSmsCode)) {
            if (phone.matches(PHONE_REGEX) && inputSmsCode.length() == 6) {
                Object smsCode = redisUtil.get(SMS_KEY_PREFIX + phone);
                if (smsCode != null && StrUtil.equals(inputSmsCode, (String) smsCode)) {
                    redisUtil.delete(SMS_KEY_PREFIX + phone);
                    return true;
                }
            }
        }
        return false;
    }
}
