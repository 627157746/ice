

package com.zhb.ice.system.api.feign;

import com.zhb.ice.common.core.constant.SecurityConstants;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.system.api.entity.SysLog;
import com.zhb.ice.system.api.feign.fallback.RemoteLogServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "remoteLogService",
        value = "ice-system-service",
        fallback = RemoteLogServiceFallbackImpl.class)
public interface RemoteLogService {

    @PostMapping("/logs")
    R<Boolean> save(@RequestBody SysLog sysLog, @RequestHeader(value = SecurityConstants.FROM_K) String from);

}
