package com.zhb.ice.common.core.constant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author zhb
 * @Description TODO 状态枚举类
 * @Date 2020/4/17 11:40
 */
@AllArgsConstructor
@NoArgsConstructor
public enum Status implements IStatus {

    /**
     * 成功！
     */
    SUCCESS(200, "success"),

    /**
     * 未找到数据！
     */
    NOT_FOUND_DATA(404, "未找到数据！"),

    /**
     * 服务器繁忙！
     */
    ERROR(500, "服务器繁忙！"),


    /**
     * 请先登录！
     */
    UNAUTHORIZED(401, "请登录！"),

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
    PARAM_NOT_MATCH(400, "参数不匹配！"),

    /**
     * 参数不能为空！
     */
    PARAM_NOT_NULL(400, "参数不能为空！"),

    /**
     * token过期或者token无效
     */
    TOKEN_INVALID(5001, "登录过期，请重新登录！"),

    /**
     * 刷新令牌无效！
     */
    INVALID_REFRESH_TOKEN(5002, "刷新令牌无效！"),

    /**
     * 无效客户端参数！
     */
    INVALID_CLIENT_PARAM(4000, "无效客户端参数！"),

    /**
     * 用户名或者密码不正确！
     */
    USERNAME_PASSWORD_ERROR(4001, "用户名或者密码不正确！"),

    /**
     * 账户被锁定！
     */
    ACCOUNT_LOCK(4002, "账户被锁定！"),

    /**
     * 手机验证码不正确！
     */
    SMS_CODE_ERROR(4002, "手机验证码不正确！"),

    /**
     * 不支持的认证类型！
     */
    NOT_SUPPORT_AUTH_TYPE(4003, "不支持的认证类型！"),

    /**
     * 第三方认证已关闭!
     */
    CLOSE_AUTH_TYPE(4003, "第三方认证已关闭！");

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }}
