package com.zhb.ice.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.security.annotation.Ignore;
import com.zhb.ice.system.api.entity.SysLog;
import com.zhb.ice.system.api.vo.LogQueryVO;
import com.zhb.ice.system.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    /**
     * @Description //TODO 保存日志
     * @Date 2020/5/25 16:11
     **/
    @Ignore
    @PostMapping
    public R save(@RequestBody SysLog sysLog) {
        return R.ofSuccess(sysLogService.save(sysLog));
    }

    /**
     * @Description //TODO 分页条件查询日志
     * @Date 2020/5/25 16:33
     **/
    @GetMapping
    @PreAuthorize("@ice.hasPermission('sys_log_query')")
    public R pageByQuery(Page<SysLog> page, LogQueryVO logQueryVO) {
        return R.ofSuccess(sysLogService.page(page, Wrappers.<SysLog>lambdaQuery()
                .like(StrUtil.isNotBlank(logQueryVO.getCreateBy()), SysLog::getCreateBy, logQueryVO.getCreateBy())
                .ge(logQueryVO.getStartTime() != null, SysLog::getCreateTime, logQueryVO.getStartTime())
                .le(logQueryVO.getEndTime() != null, SysLog::getCreateTime, logQueryVO.getEndTime())));
    }

    /**
     * @Description //TODO id查询日志
     * @Date 2020/5/25 16:34
     **/
    @GetMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_log_query')")
    public R getById(@PathVariable("id") Integer id) {
        return R.ofSuccess(sysLogService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_log_del')")
    public R delById(@PathVariable("id") Integer id) {
        if (!sysLogService.removeById(id)) {
            return R.ofStatus(Status.REMOVE_ERROR);
        }
        return R.ofSuccess();
    }

    @PostMapping("/batch/delete")
    @PreAuthorize("@ice.hasPermission('sys_log_del')")
    public R delByIds(@RequestBody List<Integer> ids) {
        if (!sysLogService.removeByIds(ids)) {
            return R.ofStatus(Status.REMOVE_ERROR);
        }
        return R.ofSuccess();
    }
}
