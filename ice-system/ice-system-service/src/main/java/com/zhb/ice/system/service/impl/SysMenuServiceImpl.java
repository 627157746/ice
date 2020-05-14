package com.zhb.ice.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.common.core.constant.MenuTypeEnum;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.system.api.entity.SysMenu;
import com.zhb.ice.system.api.entity.SysRoleMenu;
import com.zhb.ice.system.api.vo.MenuTree;
import com.zhb.ice.system.api.vo.TreeUtil;
import com.zhb.ice.system.mapper.SysMenuMapper;
import com.zhb.ice.system.service.SysMenuService;
import com.zhb.ice.system.service.SysRoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> findMenuByRoleId(Integer roleId) {
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
    public List<MenuTree> menuTree(Boolean isAll, Integer parentId) {
        LambdaQueryWrapper<SysMenu> sysMenuLambdaQueryWrapper;
        if (isAll) {
            sysMenuLambdaQueryWrapper = Wrappers.<SysMenu>lambdaQuery()
                    .orderByAsc(SysMenu::getSort);
        } else {
            sysMenuLambdaQueryWrapper = Wrappers.<SysMenu>lambdaQuery()
                    .eq(SysMenu::getType, MenuTypeEnum.LEFT_MENU.getType())
                    .orderByAsc(SysMenu::getSort);
        }
        Integer parent = parentId == null ? -1 : parentId;
        return TreeUtil.buildTree(baseMapper.selectList(sysMenuLambdaQueryWrapper), parent);
    }

    @Override
    @Transactional
    public void delById(Integer id) {
        //查询是否存在子菜单
        SysMenu existChildrenMenu = this.getOne(Wrappers.<SysMenu>lambdaQuery()
                .eq(SysMenu::getParentId, id), false);
        if (existChildrenMenu != null) {
            throw new BaseException(Status.BAD_REQUEST.getCode(), "请先删除子菜单，在删除父菜单！");
        }
        sysRoleMenuService.remove(Wrappers.<SysRoleMenu>lambdaQuery()
                .eq(SysRoleMenu::getMenuId, id));
        if (!this.removeById(id)) {
            throw new BaseException(Status.REMOVE_ERROR);
        }
    }
}
