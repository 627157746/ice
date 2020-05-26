package com.zhb.ice.system.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/26 15:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KV {

    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private Object value;
}
