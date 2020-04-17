package com.cloud.ice.common.core.constant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author zhb
 * @Description TODO 状态实现类
 * @Date 2020/4/17 11:40
 */
@AllArgsConstructor
@NoArgsConstructor
public enum Status implements IStatus {

    /**
     * 操作成功！
     */
    SUCCESS(200, "操作成功！"),

    /**
     * 没有数据！
     */
    NOT_FOUND_DATA(404, "没有数据！"),

    /**
     * 服务器繁忙！
     */
    ERROR(500, "服务器繁忙！"),

    /**
     * 登录成功！
     */
    LOGIN_SUCCESS(200, "登录成功！"),

    /**
     * 退出成功！
     */
    LOGOUT(200, "退出成功！"),

    /**
     * 请先登录！
     */
    UNAUTHORIZED(401, "请先登录！"),

    /**
     * token过期或者token无效
     */
    TOKEN_INVALID(401, "请重新登录！"),

    /**
     * 暂无权限访问！
     */
    ACCESS_DENIED(403, "权限不足！"),

    /**
     * 请求不存在！
     */
    REQUEST_NOT_FOUND(404, "请求不存在！"),

    /**
     * 请求方式不支持！
     */
    HTTP_BAD_METHOD(405, "请求方式不支持！"),

    /**
     * 请求异常！
     */
    BAD_REQUEST(400, "请求异常！"),

    /**
     * 参数不匹配！
     */
    PARAM_NOT_MATCH(500, "参数不匹配！"),

    /**
     * 参数不能为空！
     */
    PARAM_NOT_NULL(500, "参数不能为空！"),

    /**
     * 刷新令牌无效！
     */
    INVALID_REFRESH_TOKEN(400, "刷新令牌无效！"),

    /**
     * 无效客户端参数！
     */
    INVALID_CLIENT_PARAM(400, "无效客户端参数！"),

    /**
     * 用户名或者密码不正确！
     */
    USERNAME_PASSWORD_ERROR(400, "用户名或者密码不正确！"),

    /**
     * 账户被锁定！
     */
    ACCOUNT_LOCK(400, "账户被锁定！");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }}
