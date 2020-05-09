package com.zhb.ice.system.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/5/6 14:43
 */
@Data
public class TreeNode implements Serializable {

    protected int id;

    protected int parentId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<TreeNode> children = new ArrayList<>();

    public void add(TreeNode node) {
        children.add(node);
    }
}
