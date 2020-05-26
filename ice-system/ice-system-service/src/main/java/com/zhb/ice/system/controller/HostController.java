package com.zhb.ice.system.controller;

import com.zhb.ice.common.core.util.R;
import com.zhb.ice.system.api.entity.Host;
import com.zhb.ice.system.api.util.HostUtil;
import com.zhb.ice.system.api.vo.HostVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/26 15:06
 */
@RequestMapping("/hosts")
@RestController
public class HostController {

    @GetMapping
    public R getHostInfo(){
        Host host = new Host();
        host.copyTo();
        HostVO hostVO = HostUtil.wrapHostVO(host);
        return R.ofSuccess(HostUtil.wrapServerDict(hostVO));
    }
}
