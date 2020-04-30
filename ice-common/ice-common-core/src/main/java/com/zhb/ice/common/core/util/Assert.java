package com.zhb.ice.common.core.util;

import com.zhb.ice.common.core.constant.Status;
import com.zhb.ice.common.core.exception.BaseException;
import lombok.experimental.UtilityClass;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/30 10:05
 */
@UtilityClass
public class Assert {

    public void saveIsTrue(boolean expression) {
        if (!expression) {
            throw new BaseException(Status.SAVE_ERROR);
        }
    }

    public void updateIsTrue(boolean expression) {
        if (!expression) {
            throw new BaseException(Status.UPDATE_ERROR);
        }
    }

    public void removeIsTrue(boolean expression) {
        if (!expression) {
            throw new BaseException(Status.REMOVE_ERROR);
        }
    }

    public void isNotNull(Object object) {
        if (object == null) {
            throw new BaseException(Status.NOT_FOUND_DATA);
        }
    }
}
