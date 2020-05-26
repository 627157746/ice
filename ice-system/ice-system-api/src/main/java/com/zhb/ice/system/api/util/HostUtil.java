package com.zhb.ice.system.api.util;

import cn.hutool.core.lang.Dict;
import com.zhb.ice.system.api.entity.Host;
import com.zhb.ice.system.api.vo.HostVO;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/26 15:35
 */
public class HostUtil {

    public static HostVO wrapHostVO(Host host) {
        HostVO hostVO = new HostVO();
        hostVO.create(host);
        return hostVO;
    }

    public static Dict wrapServerDict(HostVO hostVO) {
        return Dict.create()
                .set("cpu", hostVO.getCpu().get(0).getData())
                .set("memory", hostVO.getMemory().get(0).getData())
                .set("sys", hostVO.getSys().get(0).getData())
                .set("jvm", hostVO.getJvm().get(0).getData())
                .set("sysFile", hostVO.getSysFile().get(0).getData());
    }
}
