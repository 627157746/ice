package com.zhb.ice.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.core.validated.Insert;
import com.zhb.ice.common.core.validated.Update;
import com.zhb.ice.common.security.annotation.Ignore;
import com.zhb.ice.common.security.util.SecurityUtils;
import com.zhb.ice.system.api.dto.SysSocialUserDTO;
import com.zhb.ice.system.api.dto.SysUserDto;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.api.validated.Register;
import com.zhb.ice.system.api.validated.UpdateUserInfo;
import com.zhb.ice.system.service.SysSocialUserService;
import com.zhb.ice.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zhb.ice.common.core.constant.SecurityConstants.PHONE;
import static com.zhb.ice.common.core.constant.SecurityConstants.USERNAME;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:40
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class SysUserController {

    private final SysUserService sysUserService;

    private final SysSocialUserService sysSocialUserService;

    /**
     * @Description //TODO 注册一个第三方用户
     * @Date 2020/4/26 9:34
     **/
    @PostMapping("/social")
    @Ignore
    public R register(@RequestBody SysSocialUserDTO sysUserDto) {
        sysUserService.register(sysUserDto.getSysUser(), sysUserDto.getSysSocialUser());
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 通过openId查询用户
     * @Date 2020/4/26 9:34
     **/
    @GetMapping("/social/{openId}")
    @Ignore
    public R<SysUser> getSysUserByOpenId(@PathVariable(value = "openId") String openId) {

        SysUser sysUser = sysSocialUserService.getSysUserByOpenId(openId);
        if (sysUser == null) {
            return R.ofStatus(Status.NOT_FOUND_DATA);
        }
        return R.ofSuccess(sysUser);
    }

    /**
     * @Description //TODO 通过用户名或者手机号查询用户,type值为phone、username
     * @Date 2020/4/23 17:25
     **/
    @GetMapping("/info/{value}")
    @Ignore
    public R<UserInfo> info(@PathVariable(value = "value") String value, @RequestParam("type") String type) {
        if (!StrUtil.equals(USERNAME, type) && !StrUtil.equals(PHONE, type)) {
            return R.ofStatus(Status.PARAM_NOT_MATCH);
        }
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.<SysUser>query()
                .lambda()
                .eq(type.equals(USERNAME), SysUser::getUsername, value)
                .eq(type.equals(PHONE), SysUser::getPhone, value);
        SysUser sysUser = sysUserService.getOne(wrapper);
        if (sysUser == null) {
            return R.ofStatus(Status.NOT_FOUND_DATA);
        }
        return R.ofSuccess(sysUserService.getUserInfo(sysUser));
    }

    /**
     * @Description //TODO id查询用户
     * @Date  2020/5/12 14:24
     **/
    @GetMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_user_query')")
    public R getById(@PathVariable("id")Integer id){
        return R.ofSuccess(sysUserService.getById(id));
    }

    /**
     * @Description //TODO 分页条件查询
     * @Date  2020/5/9 14:26
     **/
    @GetMapping
    @PreAuthorize("@ice.hasPermission('sys_user_query')")
    public R pageByQuery(Page page, SysUser sysUser){
        return R.ofSuccess(sysUserService.pageByQuery(page,sysUser));
    }

    /**
     * @Description //TODO 管理后台添加用户
     * @Date  2020/5/12 14:21
     **/
    @PostMapping
    @PreAuthorize("@ice.hasPermission('sys_user_add')")
    public R add(@Validated(Insert.class) @RequestBody SysUserDto sysUserDto){
        sysUserService.register(sysUserDto);
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 匿名用户注册
     * @Date  2020/5/12 14:21
     **/
    @PostMapping("/register")
    @Ignore(false)
    public R register(@Validated(Register.class) @RequestBody SysUserDto sysUserDto){
        sysUserService.register(sysUserDto);
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 管理后台修改用户信息
     * @Date  2020/5/12 14:22
     **/
    @PutMapping
    @PreAuthorize("@ice.hasPermission('sys_user_edit')")
    public R updateById(@Validated(Update.class)@RequestBody SysUserDto sysUserDto){
        sysUserService.update(sysUserDto);
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 用户自己修改自己信息
     * @Date  2020/5/12 14:22
     **/
    @PutMapping("/info/update")
    public R updateUserInfo(@Validated(UpdateUserInfo.class)@RequestBody SysUserDto sysUserDto){
        sysUserDto.setId(SecurityUtils.getUser().getId());
        if (!sysUserService.updateById(sysUserDto)) {
            throw new BaseException(Status.UPDATE_ERROR);
        }
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 根据id删除用户
     * @Date  2020/5/12 14:23
     **/
    @DeleteMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_user_del')")
    public R delById(@PathVariable("id") Integer id){
        sysUserService.delById(id);
        return R.ofSuccess();
    }

    /**
     * @Description //TODO ids批量删除用户
     * @Date  2020/5/12 14:23
     **/
    @PostMapping("/batch/delete")
    @PreAuthorize("@ice.hasPermission('sys_user_del')")
    public R delByIds(@RequestBody List<Integer> ids){
        sysUserService.delByIds(ids);
        return R.ofSuccess();
    }
}
