package com.zhb.ice.system.api.vo;

import cn.hutool.core.collection.ListUtil;
import com.zhb.ice.system.api.entity.Host;
import com.zhb.ice.system.api.vo.host.*;
import lombok.Data;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/26 15:26
 */
@Data
public class HostVO {

    List<CpuVO> cpu = ListUtil.list(false);
    List<JvmVO> jvm = ListUtil.list(false);
    List<MemoryVO> memory = ListUtil.list(false);
    List<SysFileVO> sysFile = ListUtil.list(false);
    List<SysVO> sys = ListUtil.list(false);

    public HostVO create(Host host) {
        cpu.add(CpuVO.create(host.getCpu()));
        jvm.add(JvmVO.create(host.getJvm()));
        memory.add(MemoryVO.create(host.getMemory()));
        sysFile.add(SysFileVO.create(host.getSysFiles()));
        sys.add(SysVO.create(host.getSys()));
        return null;
    }
}
