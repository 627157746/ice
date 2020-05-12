package com.zhb.ice.system.api.vo;

import com.zhb.ice.system.api.entity.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/6 14:43
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends TreeNode implements Serializable {

    private String name;

    private String permission;

    private String path;

    private String icon;

    private String component;

    private Integer sort;

    private Boolean noCache;

    private Boolean hidden;

    private String type;

    private Long createTime;

    private Long updateTime;

    public MenuTree(SysMenu sysMenu) {
        this.id = sysMenu.getId();
        this.parentId = sysMenu.getParentId();
        this.icon = sysMenu.getIcon();
        this.component = sysMenu.getComponent();
        this.name = sysMenu.getName();
        this.path = sysMenu.getPath();
        this.sort = sysMenu.getSort();
        this.noCache = sysMenu.getNoCache();
        this.hidden = sysMenu.getHidden();
    }
}
