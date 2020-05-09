package com.zhb.ice.system.api.vo;

import com.zhb.ice.system.api.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/9 10:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserVO extends SysUser implements Serializable {

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色列表
     */
    private List<Integer> roleIds;
}
