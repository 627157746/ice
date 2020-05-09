package com.zhb.ice.system.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.zhb.ice.common.core.constant.RegexConstants.PHONE_REGEX;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/24 11:15
 */
@Data
public class SmsLogin implements Serializable {


    @Pattern(regexp = PHONE_REGEX, message = "手机号不正确！")
    @NotEmpty(message = "手机号不能为空！")
    private String phone;

}
