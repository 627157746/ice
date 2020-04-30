package com.zhb.ice.system.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/30 15:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_log")
public class SysLog {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String type;

    private String title;

    private String serviceId;

    private String createBy;

    private String createTime;

    private String ip;

    private String userAgent;

    private String requestUri;

    private String method;

    private String params;

    private int time;

    @TableField("is_del")
    @TableLogic
    private boolean del;

}
