package com.zhb.ice.common.core.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhb.ice.common.core.constant.IStatus;
import com.zhb.ice.common.core.exception.BaseException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zhb
 * @Description TODO Response响应封装
 * @Date 2020/4/23 9:06
 */
public class ResponseUtil {

    public static void renderJson(HttpServletResponse response, IStatus status) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter()
                .write(JSONUtil.toJsonStr(new JSONObject(R.ofStatus(status), false)));
    }

    public static void renderJson(HttpServletResponse response, BaseException exception) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter()
                .write(JSONUtil.toJsonStr(new JSONObject(R.ofException(exception), false)));
    }

}
