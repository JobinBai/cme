package com.baiyanbing.cme.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author baiyanbing
 * @createAt 2019-11-18 13:59
 */

@Data
@TableName(schema="public", value = "category")
public class Category implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    @TableField(value = "name")
    private String name;

    private static final long serialVersionUID = 1L;
}