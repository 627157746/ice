package com.zhb.ice.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import com.zhb.ice.system.api.entity.SysDict;
import com.zhb.ice.system.api.entity.SysDictItem;
import com.zhb.ice.system.mapper.SysDictMapper;
import com.zhb.ice.system.service.SysDictItemService;
import com.zhb.ice.system.service.SysDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/15 10:32
 */
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final SysDictItemService sysDictItemService;

    @Override
    @Transactional
    public void delById(Integer id) {

        sysDictItemService.remove(Wrappers.<SysDictItem>lambdaQuery()
                .eq(SysDictItem::getDictId, id));
        if (!this.removeById(id)) {
            throw new BaseException(Status.REMOVE_ERROR);
        }
    }
}
