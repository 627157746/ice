package com.zhb.ice.common.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/6 17:13
 */
@AllArgsConstructor
@Getter
public enum MenuTypeEnum {

    /**
     * 左侧菜单
     */
    LEFT_MENU("0","left"),

    /**
     * 顶部菜单
     */
    TOP_MENU("2","top"),

    /**
     * 按钮
     */
    BUTTON("1","button");

    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
}
