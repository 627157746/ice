package com.zhb.ice.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/1 14:51
 */
@Component
public class IceMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        long currentTime = System.currentTimeMillis();
        this.strictInsertFill(metaObject, "createTime", Long.class, currentTime);
        this.strictInsertFill(metaObject, "updateTime", Long.class, currentTime);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Long.class, System.currentTimeMillis());
    }
}
