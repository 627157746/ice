package com.zhb.ice.system.api.vo.host;

import cn.hutool.core.collection.ListUtil;
import com.zhb.ice.system.api.entity.host.Cpu;
import com.zhb.ice.system.api.vo.KV;
import lombok.Data;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/26 15:27
 */
@Data
public class CpuVO {

    List<KV> data = ListUtil.list(false);

    public static CpuVO create(Cpu cpu) {
        CpuVO vo = new CpuVO();
        vo.data.add(new KV("核心数", cpu.getCpuNum()));
        vo.data.add(new KV("CPU系统使用率", cpu.getSys() + "%"));
        vo.data.add(new KV("CPU用户使用率", cpu.getUsed() + "%"));
        vo.data.add(new KV("CPU当前空闲率", cpu.getFree() + "%"));
        return vo;
    }
}
