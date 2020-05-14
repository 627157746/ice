package com.zhb.ice.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhb.ice.system.api.entity.SysDept;
import com.zhb.ice.system.api.vo.DeptTree;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/8 16:41
 */
public interface SysDeptService  extends IService<SysDept> {
    List<DeptTree> listDeptTrees(Integer id);

    void delById(Integer id);
}
