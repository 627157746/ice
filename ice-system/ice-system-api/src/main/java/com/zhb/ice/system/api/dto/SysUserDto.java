package com.zhb.ice.system.api.dto;

import com.zhb.ice.common.core.validated.Insert;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.api.validated.Register;
import com.zhb.ice.system.api.validated.UpdateUserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/11 15:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDto extends SysUser {

    @NotEmpty(groups = {Insert.class}, message = "角色不能为空！")
    @Null(groups = {Register.class, UpdateUserInfo.class})
    private List<Integer> roleIds;
}
