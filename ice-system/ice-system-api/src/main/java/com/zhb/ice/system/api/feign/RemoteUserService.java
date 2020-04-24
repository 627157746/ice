

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
 * @Description TODO
 * @Date 2020/4/20 20:42
 */
@FeignClient(contextId = "remoteUserService", name = "ice-system-service", value = "ice-system-service", fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

    @PostMapping("/users/social")
    R register(@RequestBody SysUserDto sysUserDto,
               @RequestHeader(value = SecurityConstants.FROM_K) String from);

    @GetMapping("/users/social/{openId}")
    R<SysUser> getSysUserByOpenId(@PathVariable(value = "openId") String openId,
                                  @RequestHeader(value = SecurityConstants.FROM_K) String from);

    @GetMapping("/users/info/{value}")
    R<UserInfo> info(@PathVariable(value = "value") String value,
                     @RequestParam(value = "type") String type,
                     @RequestHeader(value = SecurityConstants.FROM_K) String from);

}
