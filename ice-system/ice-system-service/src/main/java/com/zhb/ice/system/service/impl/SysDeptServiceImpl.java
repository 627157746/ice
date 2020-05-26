package com.zhb.ice.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.system.api.entity.SysDept;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.api.vo.DeptTree;
import com.zhb.ice.system.api.util.TreeUtil;
import com.zhb.ice.system.mapper.SysDeptMapper;
import com.zhb.ice.system.service.SysDeptService;
import com.zhb.ice.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    private final SysUserService sysUserService;

    @Override
    public List<DeptTree> listDeptTrees(Integer id) {
        return getDeptTree(this.list(), id);
    }

    @Override
    public void delById(Integer id) {
        SysUser existUser = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getDeptId, id), false);
        if (existUser != null) {
            throw new BaseException(Status.BAD_REQUEST.getCode(), "该部门含有关联的用户！");
        }
        if (!this.removeById(id)) {
            throw new BaseException(Status.REMOVE_ERROR);
        }
    }

    private List<DeptTree> getDeptTree(List<SysDept> depts, Integer id) {
        List<DeptTree> treeList = depts.stream()
                .sorted(Comparator.comparingInt(SysDept::getSort))
                .map(dept -> {
                    DeptTree node = new DeptTree();
                    node.setId(dept.getId());
                    node.setParentId(dept.getParentId());
                    node.setLabel(dept.getName());
                    node.setName(dept.getName());
                    node.setSort(dept.getSort());
                    node.setCreateTime(dept.getCreateTime());
                    node.setUpdateTime(dept.getUpdateTime());
                    if (id != null && node.getId() == id) {
                        node.setDisabled(true);
                    } else {
                        node.setDisabled(false);
                    }
                    return node;
                }).collect(Collectors.toList());
        return TreeUtil.build(treeList, 0);
    }
}
