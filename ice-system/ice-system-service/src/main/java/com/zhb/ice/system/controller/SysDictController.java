package com.zhb.ice.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.util.R;
import com.zhb.ice.common.core.validated.Insert;
import com.zhb.ice.common.core.validated.Update;
import com.zhb.ice.system.api.entity.SysDict;
import com.zhb.ice.system.api.entity.SysDictItem;
import com.zhb.ice.system.service.SysDictItemService;
import com.zhb.ice.system.service.SysDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/15 10:34
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dicts")
public class SysDictController {

    private final SysDictService sysDictService;

    private final SysDictItemService sysDictItemService;

    /**
     * @Description //TODO 分页查询字典
     * @Date 2020/5/15 10:58
     **/
    @GetMapping
    public R dictPageByQuery(Page<SysDict> page, SysDict sysDict) {
        return R.ofSuccess(sysDictService.page(page, Wrappers
                .<SysDict>lambdaQuery()
                .like(StrUtil.isNotBlank(sysDict.getName()), SysDict::getName, sysDict.getName())
                .or()
                .like(StrUtil.isNotBlank(sysDict.getType()), SysDict::getType, sysDict.getType())));
    }

    /**
     * @Description //TODO 根据id查询字典
     * @Date 2020/5/15 10:58
     **/
    @GetMapping("/{id}")
    public R dictGetById(@PathVariable("id") Integer id) {
        return R.ofSuccess(sysDictService.getById(id));
    }

    /**
     * @Description //TODO 添加字典
     * @Date 2020/5/15 10:58
     **/
    @PostMapping
    @PreAuthorize("@ice.hasPermission('sys_dict_add')")
    public R dictAdd(@Validated(Insert.class) @RequestBody SysDict sysDict) {
        if (!sysDictService.save(sysDict)) {
            return R.ofStatus(Status.SAVE_ERROR);
        }
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 修改字典
     * @Date 2020/5/15 10:58
     **/
    @PutMapping
    @PreAuthorize("@ice.hasPermission('sys_dict_edit')")
    public R dictUpdate(@Validated(Update.class) @RequestBody SysDict sysDict) {
        if (sysDictService.getById(sysDict.getId()).getIsSystem()) {
            return R.of(Status.BAD_REQUEST.getCode(), "系统类字典不允许修改！", null);
        }
        if (!sysDictService.updateById(sysDict)) {
            return R.ofStatus(Status.UPDATE_ERROR);
        }
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 根据id删除字典
     * @Date 2020/5/15 10:59
     **/
    @DeleteMapping("/{id}")
    @PreAuthorize("@ice.hasPermission('sys_dict_del')")
    public R dictDelById(@PathVariable("id") Integer id) {
        sysDictService.delById(id);
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 根据字典类型查询所有字典数据
     * @Date 2020/5/15 10:59
     **/
    @GetMapping("/items/type/{type}")
    public R dictItemGetByType(@PathVariable("type") String type) {
        return R.ofSuccess(sysDictItemService.list(Wrappers.<SysDictItem>lambdaQuery()
                .eq(SysDictItem::getType, type)
                .orderByAsc(SysDictItem::getSort)));
    }

    /**
     * @Description //TODO 分页查询字典数据
     * @Date 2020/5/15 10:59
     **/
    @GetMapping("/items")
    public R dictItemPageByQuery(Page<SysDictItem> page, SysDictItem sysDictItem) {
        return R.ofSuccess(sysDictItemService.page(page, Wrappers
                .<SysDictItem>lambdaQuery()
                .eq(SysDictItem::getDictId,sysDictItem.getDictId())
                .like(StrUtil.isNotBlank(sysDictItem.getLabel()), SysDictItem::getLabel, sysDictItem.getLabel())
                .orderByAsc(SysDictItem::getSort)));
    }

    /**
     * @Description //TODO 根据id查询字典数据
     * @Date 2020/5/15 10:59
     **/
    @GetMapping("/items/{id}")
    public R dictItemGetById(@PathVariable("id") Integer id) {
        return R.ofSuccess(sysDictItemService.getById(id));
    }

    /**
     * @Description //TODO 添加字典数据
     * @Date 2020/5/15 10:59
     **/
    @PostMapping("/items")
    @PreAuthorize("@ice.hasPermission('sys_dict_add')")
    public R dictItemAdd(@Validated(Insert.class) @RequestBody SysDictItem sysDictItem) {
        if (!sysDictItemService.save(sysDictItem)) {
            return R.ofStatus(Status.SAVE_ERROR);
        }
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 修改字典数据
     * @Date 2020/5/15 11:00
     **/
    @PutMapping("/items")
    @PreAuthorize("@ice.hasPermission('sys_dict_edit')")
    public R dictItemUpdate(@Validated(Update.class) @RequestBody SysDictItem sysDictItem) {
        if (sysDictService.getById(sysDictItem.getDictId()).getIsSystem()) {
            return R.of(Status.BAD_REQUEST.getCode(), "系统类字典不允许修改！", null);
        }
        if (!sysDictItemService.updateById(sysDictItem)) {
            return R.ofStatus(Status.UPDATE_ERROR);
        }
        return R.ofSuccess();
    }

    /**
     * @Description //TODO 根据id删除字典数据
     * @Date 2020/5/15 11:00
     **/
    @DeleteMapping("/items/{id}")
    @PreAuthorize("@ice.hasPermission('sys_dict_del')")
    public R dictItemDelById(@PathVariable("id") Integer id) {
        if (sysDictService.getById(sysDictItemService.getById(id).getDictId()).getIsSystem()) {
            return R.of(Status.BAD_REQUEST.getCode(), "系统类字典不允许删除！", null);
        }
        if (!sysDictItemService.removeById(id)) {
            return R.ofStatus(Status.REMOVE_ERROR);
        }
        return R.ofSuccess();
    }


}
