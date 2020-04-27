

package com.zhb.ice.system.api.feign;

import com.zhb.ice.common.core.constant.SecurityConstants;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.system.api.dto.SysUserDto;
import com.zhb.ice.system.api.dto.UserInfo;
import com.zhb.ice.system.api.entity.SysUser;
import com.zhb.ice.system.api.feign.factory.RemoteUserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhb
 * @Description TODO 用户信息feign客户端
 * @Date 2020/4/20 20:42
 */
@FeignClient(contextId = "remoteUserService", name = "ice-system-service", value = "ice-system-service", fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

    /**
     * @Description //TODO 注册一个第三方用户
     * @Date  2020/4/26 9:31
     **/
    @PostMapping("/users/social")
    R register(@RequestBody SysUserDto sysUserDto,
               @RequestHeader(value = SecurityConstants.FROM_K) String from);

    /**
     * @Description //TODO 通过openId查询用户
     * @Date  2020/4/26 9:31
     **/
    @GetMapping("/users/social/{openId}")
    R<SysUser> getSysUserByOpenId(@PathVariable(value = "openId") String openId,
                                  @RequestHeader(value = SecurityConstants.FROM_K) String from);

    /**
     * @Description //TODO 通过用户名或者手机号查询用户,type值为phone、username
     * @Date  2020/4/26 9:32
     **/
    @GetMapping("/users/info/{value}")
    R<UserInfo> info(@PathVariable(value = "value") String value,
                     @RequestParam(value = "type") String type,
                     @RequestHeader(value = SecurityConstants.FROM_K) String from);

}
