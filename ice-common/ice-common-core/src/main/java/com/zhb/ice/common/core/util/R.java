package com.zhb.ice.common.core.util;

import com.zhb.ice.common.core.constant.IStatus;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.common.core.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author zhb
 * @Description TODO 返回结果封装
 * @Date 2020/4/17 11:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {


    private int code;
    private String msg;
    private T data;

    public static <T> R<T> of(Integer code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    public static <T> R<T> ofSuccess() {
        return ofSuccess(null);
    }

    public static <T> R<T> ofSuccess(T data) {
        return ofStatus(Status.SUCCESS, data);
    }

    public static <T> R<T> ofError() {
        return ofStatus(Status.ERROR);
    }

    public static <T> R<T> ofMsg(String msg) {
        return of(Status.SUCCESS.getCode(), msg, null);
    }

    public static <T> R<T> ofStatus(IStatus status) {
        return ofStatus(status, null);
    }

    public static <T> R<T> ofStatus(IStatus status, T data) {
        return of(status.getCode(), status.getMsg(), data);
    }

    public static <T extends BaseException> R ofException(T t) {
        return of(t.getCode(), t.getMsg(), null);
    }

}
