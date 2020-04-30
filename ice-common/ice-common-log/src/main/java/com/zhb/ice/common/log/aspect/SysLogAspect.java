package com.zhb.ice.common.log.aspect;

import com.zhb.ice.common.core.util.SpringContextHolder;
import com.zhb.ice.common.log.annotation.SysLog;
import com.zhb.ice.common.log.event.SysLogEvent;
import com.zhb.ice.common.log.util.SysLogUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/30 16:33
 */
@Aspect
@Slf4j
@RequiredArgsConstructor
public class SysLogAspect {

    @Around("@annotation(sysLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.info("[类名]:{},[方法]:{}", strClassName, strMethodName);
        com.zhb.ice.system.api.entity.SysLog sysLogVo = SysLogUtils.getSysLog();
        sysLogVo.setTitle(sysLog.value());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        sysLogVo.setTime((int) (endTime - startTime));
        SpringContextHolder.publishEvent(new SysLogEvent(sysLogVo));
        return obj;
    }


}
