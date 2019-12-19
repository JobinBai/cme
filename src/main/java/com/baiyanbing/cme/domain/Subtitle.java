package com.baiyanbing.cme.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author baiyanbing
 * @createAt 2019/12/17 13:39
 */

@Data
@TableName(value = "subtitle")
public class Subtitle {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电视名称
     */
    @TableField(value = "tv_name")
    private String tvName;

    /**
     * 季
     */
    @TableField(value = "season")
    private Integer season;

    /**
     * 集
     */
    @TableField(value = "episode")
    private Integer episode;

    /**
     * 行
     */
    @TableField(value = "row")
    private Integer row;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;
}