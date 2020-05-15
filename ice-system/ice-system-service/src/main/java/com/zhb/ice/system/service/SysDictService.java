package com.zhb.ice.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhb.ice.system.api.entity.SysDict;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/15 10:31
 */
public interface SysDictService extends IService<SysDict> {
    void delById(Integer id);
}
