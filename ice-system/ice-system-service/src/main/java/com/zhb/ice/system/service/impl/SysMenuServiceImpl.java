package com.zhb.ice.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.common.core.constant.MenuTypeEnum;
import com.zhb.ice.system.api.entity.SysMenu;
import com.zhb.ice.system.api.vo.MenuTree;
import com.zhb.ice.system.api.vo.TreeUtil;
import com.zhb.ice.system.mapper.SysMenuMapper;
import com.zhb.ice.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 21:11
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findMenuByRoleId(int roleId) {
        return sysMenuMapper.listMenusByRoleId(roleId);
    }

    @Override
    public List<MenuTree> filterMenu(Set<SysMenu> all, Integer parentId) {
        List<MenuTree> menuTreeList = all.stream()
                .filter(vo -> MenuTypeEnum.LEFT_MENU.getType().equals(vo.getType()))
                .map(MenuTree::new)
                .sorted(Comparator.comparingInt(MenuTree::getSort))
                .collect(Collectors.toList());
        Integer parent = parentId == null ? -1 : parentId;
        return TreeUtil.build(menuTreeList, parent);
    }

    @Override
    public List<MenuTree> menuTree(Integer parentId) {
        return TreeUtil.buildTree(baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery()
                .orderByAsc(SysMenu::getSort)), -1);
    }
}
