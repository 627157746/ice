package com.zhb.ice.common.core.constant;

/**
 * @Author zhb
 * @Description TODO 状态接口
 * @Date 2020/4/17 11:40
 */
public interface IStatus {

    /**
     * 状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 返回信息
     *
     * @return 返回信息
     */
    String getMsg();
}
