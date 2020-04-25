package com.zhb.ice.common.core.exception;

import com.zhb.ice.common.core.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zhb
 * @Description TODO 自定义基础异常类
 * @Date 2020/4/17 11:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private Integer code;
    private String msg;

    public BaseException(Status status) {
        super(status.getMsg());
        this.code = status.getCode();
        this.msg = status.getMsg();
    }
}
