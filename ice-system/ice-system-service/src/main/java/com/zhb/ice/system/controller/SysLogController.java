package com.zhb.ice.system.controller;

import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.security.annotation.Ignore;
import com.zhb.ice.system.api.entity.SysLog;
import com.zhb.ice.system.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/30 15:38
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/logs")
public class SysLogController {

    private final SysLogService sysLogService;

    @Ignore
    @PostMapping
    public R save(@RequestBody SysLog sysLog) {
        return R.ofSuccess(sysLogService.save(sysLog));
    }
}
