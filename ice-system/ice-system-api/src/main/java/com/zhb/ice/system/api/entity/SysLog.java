package com.zhb.ice.system.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/30 15:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_log")
public class SysLog implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String serviceId;

    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    private String ip;

    private String userAgent;

    private String requestUri;

    private String method;

    private String params;

    private Integer time;

    @TableField(value = "is_del")
    @TableLogic
    private Boolean del;

}
