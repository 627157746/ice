package com.zhb.ice.system.api.dto;

import com.zhb.ice.system.api.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/11 15:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDto extends SysUser {

    private List<Integer> roleIds;
}
