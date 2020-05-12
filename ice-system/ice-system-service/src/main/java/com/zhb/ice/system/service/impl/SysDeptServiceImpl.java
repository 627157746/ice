package com.zhb.ice.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.system.api.entity.SysDept;
import com.zhb.ice.system.api.vo.DeptTree;
import com.zhb.ice.system.api.vo.TreeUtil;
import com.zhb.ice.system.mapper.SysDeptMapper;
import com.zhb.ice.system.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/8 16:41
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    @Override
    public List<DeptTree> listDeptTrees() {
        return getDeptTree(this.list());
    }

    private List<DeptTree> getDeptTree(List<SysDept> depts) {
        List<DeptTree> treeList = depts.stream()
//                .filter(dept -> !dept.getDel().equals(dept.getParentId()))
                .sorted(Comparator.comparingInt(SysDept::getSort))
                .map(dept -> {
                    DeptTree node = new DeptTree();
                    node.setId(dept.getId());
                    node.setParentId(dept.getParentId());
                    node.setLabel(dept.getName());
                    return node;
                }).collect(Collectors.toList());
        return TreeUtil.build(treeList, 0);
    }
}
