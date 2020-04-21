package com.zhb.ice.common.core.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhb.ice.common.core.constant.IStatus;
import com.zhb.ice.common.core.exception.BaseException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    /**
     * 往 response 写出 json
     *
     * @param response 响应
     * @param status   状态
     */
    public static void renderJson(HttpServletResponse response, IStatus status) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter()
                .write(JSONUtil.toJsonStr(new JSONObject(R.ofStatus(status), false)));
    }

    /**
     * 往 response 写出 json
     *
     * @param response  响应
     * @param exception 异常
     */
    public static void renderJson(HttpServletResponse response, BaseException exception) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter()
                .write(JSONUtil.toJsonStr(new JSONObject(R.ofException(exception), false)));
    }

}
