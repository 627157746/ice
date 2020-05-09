package com.zhb.ice.system.api.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/8 16:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode implements Serializable {

    private String label;
}
