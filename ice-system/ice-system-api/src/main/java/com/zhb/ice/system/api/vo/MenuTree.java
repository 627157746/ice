package com.zhb.ice.system.api.vo;

import com.zhb.ice.system.api.entity.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/6 14:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends TreeNode implements Serializable {

    private String name;

//    private String permission;

    private String path;

    private String icon;

    private String component;

    private Integer sort;

    private Boolean keepAlive;

//    private String type;

    public MenuTree(SysMenu sysMenu) {
        this.id = sysMenu.getId();
        this.parentId = sysMenu.getParentId();
        this.icon = sysMenu.getIcon();
        this.component = sysMenu.getComponent();
        this.name = sysMenu.getName();
        this.path = sysMenu.getPath();
//        this.type = sysMenu.getType();
//        this.permission = sysMenu.getPermission();
        this.sort = sysMenu.getSort();
        this.keepAlive = sysMenu.getKeepAlive();
    }
}
