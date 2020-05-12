package com.zhb.ice.system.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/24 16:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_social_user")
public class SysSocialUser implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String type;

    private String accessToken;

    private String refreshToken;

    private String openId;

    private Integer uid;

    @TableField(fill = FieldFill.INSERT,updateStrategy = FieldStrategy.NEVER)
    private Long createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @TableField(value = "is_del")
    @TableLogic
    private Boolean del;
}
