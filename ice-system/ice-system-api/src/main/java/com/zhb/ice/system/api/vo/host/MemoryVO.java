package com.zhb.ice.system.api.vo.host;

import cn.hutool.core.collection.ListUtil;
import com.zhb.ice.system.api.entity.host.Memory;
import com.zhb.ice.system.api.vo.KV;
import lombok.Data;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/26 15:29
 */
@Data
public class MemoryVO {

    List<KV> data = ListUtil.list(false);

    public static MemoryVO create(Memory memory) {
        MemoryVO vo = new MemoryVO();
        vo.data.add(new KV("内存总量", memory.getTotal() + "G"));
        vo.data.add(new KV("已用内存", memory.getUsed() + "G"));
        vo.data.add(new KV("剩余内存", memory.getFree() + "G"));
        vo.data.add(new KV("使用率", memory.getUsage() + "%"));
        return vo;
    }
}
