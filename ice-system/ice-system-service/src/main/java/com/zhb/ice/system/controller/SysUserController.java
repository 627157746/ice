package com.zhb.ice.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.security.annotation.Ignore;
import com.zhb.ice.system.api.dto.SysSocialUserDTO;
import com.zhb.ice.system.api.dto.SysUserDto;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.service.SysSocialUserService;
import com.zhb.ice.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    int port;

    @GetMapping("/{id}")
    public R getById(@PathVariable("id")Integer id){
        return R.ofSuccess(sysUserService.getById(id));
    }

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
        log.info("端口为:{}", port);
        SysUser sysUser = sysUserService.getOne(wrapper);
        if (sysUser == null) {
            return R.ofStatus(Status.NOT_FOUND_DATA);
        }
        return R.ofSuccess(sysUserService.getUserInfo(sysUser));
    }

    /**
     * @Description //TODO 分页条件查询
     * @Date  2020/5/9 14:26
     **/
    @GetMapping
    public R pageByQuery(Page page, SysUser sysUser){
        return R.ofSuccess(sysUserService.pageByQuery(page,sysUser));
    }

    @PostMapping
    public R register(@Validated @RequestBody SysUserDto sysUserDto){
        sysUserService.register(sysUserDto);
        return R.ofSuccess();
    }
    @PutMapping
    @PreAuthorize("@ice.hasPermission('sys_user_edit')")
    public R updateById(@RequestBody SysUserDto sysUserDto){
        sysUserService.update(sysUserDto);
        return R.ofSuccess();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_user_del')")
    public R delById(@PathVariable("id") Integer id){
        sysUserService.delById(id);
        return R.ofSuccess();
    }

    @PostMapping("/batch/delete")
    @PreAuthorize("@ice.hasPermission('sys_user_del')")
    public R delByIds(@RequestBody List<Integer> ids){
        sysUserService.delByIds(ids);
        return R.ofSuccess();
    }
}
