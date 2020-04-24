package com.zhb.ice.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.security.annotation.Ignore;
import com.zhb.ice.system.api.dto.SysUserDto;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.service.SysSocialUserService;
import com.zhb.ice.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import static com.zhb.ice.common.core.constant.SecurityConstants.PHONE;
import static com.zhb.ice.common.core.constant.SecurityConstants.USERNAME;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/20 20:40
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class SysUserController {

    private final SysUserService sysUserService;

    private final SysSocialUserService sysSocialUserService;

    @Value("${server.port}")
    int port;

    @PostMapping("/social")
    @Ignore
    public R register(@RequestBody SysUserDto sysUserDto) {
        sysUserService.register(sysUserDto.getSysUser(), sysUserDto.getSysSocialUser());
        return R.ofSuccess();
    }


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
     * @Description //TODO 根据type来获取用户的全部信息
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
        System.out.println("端口为:" + port);
        SysUser sysUser = sysUserService.getOne(wrapper);
        if (sysUser == null) {
            return R.ofStatus(Status.NOT_FOUND_DATA);
        }
        return R.ofSuccess(sysUserService.getUserInfo(sysUser));
    }
}
