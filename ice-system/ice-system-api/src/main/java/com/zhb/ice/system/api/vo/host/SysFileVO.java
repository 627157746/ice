package com.zhb.ice.system.api.vo.host;

import cn.hutool.core.collection.ListUtil;
import com.zhb.ice.system.api.entity.host.SysFile;
import com.zhb.ice.system.api.vo.KV;
import lombok.Data;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/26 15:30
 */
@Data
public class SysFileVO {

    List<List<KV>> data = ListUtil.list(false);

    public static SysFileVO create(List<SysFile> sysFiles) {
        SysFileVO vo = new SysFileVO();
        for (SysFile sysFile : sysFiles) {
            List<KV> item = ListUtil.list(false);

            item.add(new KV("盘符路径", sysFile.getDirName()));
            item.add(new KV("盘符类型", sysFile.getSysTypeName()));
            item.add(new KV("文件类型", sysFile.getTypeName()));
            item.add(new KV("总大小", sysFile.getTotal()));
            item.add(new KV("剩余大小", sysFile.getFree()));
            item.add(new KV("已经使用量", sysFile.getUsed()));
            item.add(new KV("资源的使用率", sysFile.getUsage() + "%"));

            vo.data.add(item);
        }
        return vo;
    }
}
