package com.zhb.ice.system.api.vo.host;

import cn.hutool.core.collection.ListUtil;
import com.zhb.ice.system.api.entity.host.Sys;
import com.zhb.ice.system.api.vo.KV;
import lombok.Data;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/26 15:31
 */
@Data
public class SysVO {

    List<KV> data = ListUtil.list(false);

    public static SysVO create(Sys sys) {
        SysVO vo = new SysVO();
        vo.data.add(new KV("服务器名称", sys.getComputerName()));
        vo.data.add(new KV("服务器ip", sys.getComputerIp()));
        vo.data.add(new KV("项目路径", sys.getUserDir()));
        vo.data.add(new KV("操作系统", sys.getOsName()));
        vo.data.add(new KV("系统架构", sys.getOsArch()));
        return vo;
    }
}
