package com.zhb.ice.system.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.zhb.ice.common.core.validated.Insert;
import com.zhb.ice.common.core.validated.Update;
import com.zhb.ice.system.api.validated.Register;
import com.zhb.ice.system.api.validated.UpdateUserInfo;
import lombok.*;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = {Update.class}, message = "主键不能为空！")
    private Integer id;

    @NotEmpty(groups = {Insert.class, Register.class}, message = "用户名不能为空！")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private String username;

    @NotEmpty(groups = {Insert.class, Register.class}, message = "密码不能为空！")
    private String password;

    @NotEmpty(groups = {Insert.class, Register.class}, message = "昵称不能为空！")
    private String nickname;

    @NotEmpty(groups = {Insert.class, Register.class}, message = "手机号不能为空！")
    private String phone;

    private String avatar;

    private String remarks;

    @NotNull(groups = {Insert.class}, message = "部门不能为空！")
    @Null(groups = {Register.class, UpdateUserInfo.class})
    private Integer deptId;

    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    @Null(groups = {Insert.class, Register.class, UpdateUserInfo.class})
    private Long createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Null(groups = {Insert.class, Register.class, UpdateUserInfo.class})
    private Long updateTime;

    @TableField("is_lock_account")
    @AssertFalse(groups = {Insert.class})
    @Null(groups = {Register.class, UpdateUserInfo.class})
    private Boolean lockAccount;

    @TableField(value = "is_del")
    @TableLogic
    private Boolean del;
}
