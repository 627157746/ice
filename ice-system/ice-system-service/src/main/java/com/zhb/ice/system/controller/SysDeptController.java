package com.zhb.ice.system.controller;

import com.zhb.ice.common.core.util.R;
import com.zhb.ice.system.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/8 16:23
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/depts")
public class SysDeptController {

    private final SysDeptService sysDeptService;

    @GetMapping(value = "/tree")
    public R listDeptTrees() {
        return R.ofSuccess(sysDeptService.listDeptTrees());
    }
}
