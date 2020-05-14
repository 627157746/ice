package com.zhb.ice.system.controller;

import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.core.validated.Insert;
import com.zhb.ice.common.core.validated.Update;
import com.zhb.ice.system.api.entity.SysDept;
import com.zhb.ice.system.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/tree")
    public R listDeptTrees(Integer id) {
        return R.ofSuccess(sysDeptService.listDeptTrees(id));
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable("id")Integer id){
        return R.ofSuccess(sysDeptService.getById(id));
    }

    @PostMapping
    @PreAuthorize("@ice.hasPermission('sys_dept_add')")
    public R add(@Validated(Insert.class)@RequestBody SysDept sysDept){
        if (!sysDeptService.save(sysDept)) {
            return R.ofStatus(Status.SAVE_ERROR);
        }
        return R.ofSuccess();
    }

    @PutMapping
    @PreAuthorize("@ice.hasPermission('sys_dept_edit')")
    public R update(@Validated(Update.class)@RequestBody SysDept sysDept){
        if (!sysDeptService.updateById(sysDept)) {
            return R.ofStatus(Status.UPDATE_ERROR);
        }
        return R.ofSuccess();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_dept_del')")
    public R delById(@PathVariable("id") Integer id){
        sysDeptService.delById(id);
        return R.ofSuccess();
    }
}
