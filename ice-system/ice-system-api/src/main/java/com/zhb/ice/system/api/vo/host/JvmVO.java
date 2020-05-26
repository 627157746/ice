package com.zhb.ice.system.api.vo.host;

import cn.hutool.core.collection.ListUtil;
import com.zhb.ice.system.api.entity.host.Jvm;
import com.zhb.ice.system.api.vo.KV;
import lombok.Data;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/26 15:29
 */
@Data
public class JvmVO {

    List<KV> data = ListUtil.list(false);

    public static JvmVO create(Jvm jvm) {
        JvmVO vo = new JvmVO();
        vo.data.add(new KV("当前JVM占用的内存总数(M)", jvm.getTotal() + "M"));
        vo.data.add(new KV("JVM最大可用内存总数(M)", jvm.getMax() + "M"));
        vo.data.add(new KV("JVM空闲内存(M)", jvm.getFree() + "M"));
        vo.data.add(new KV("JVM使用率", jvm.getUsage() + "%"));
        vo.data.add(new KV("JDK版本", jvm.getVersion()));
        vo.data.add(new KV("JDK路径", jvm.getHome()));
        vo.data.add(new KV("JDK启动时间", jvm.getStartTime()));
        vo.data.add(new KV("JDK运行时间", jvm.getRunTime()));
        return vo;
    }
}
