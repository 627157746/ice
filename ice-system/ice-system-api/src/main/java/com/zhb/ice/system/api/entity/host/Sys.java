package com.zhb.ice.system.api.entity.host;

import lombok.Data;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/26 14:41
 */
@Data
public class Sys {

    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;
}
