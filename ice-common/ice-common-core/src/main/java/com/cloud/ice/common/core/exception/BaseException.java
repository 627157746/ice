package com.cloud.ice.common.core.exception;

import com.cloud.ice.common.core.constant.Status;
import lombok.Data;

/**
 * @Author zhb
 * @Description TODO 自定义基础异常类
 * @Date 2020/4/17 11:40
 */
@Data
public class BaseException extends RuntimeException {

    private Integer code;
    private String msg;

    public BaseException(Status status) {
        super(status.getMsg());
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
