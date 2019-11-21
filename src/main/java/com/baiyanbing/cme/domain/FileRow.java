package com.baiyanbing.cme.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author baiyanbing
 * @createAt 2019-11-18 18:43
 */

@Data
@TableName(schema="public", value = "file_row")
public class FileRow implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "file_source_id")
    private Integer fileSourceId;

    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 行号
     */
    @TableField(value = "row_no")
    private Integer rowNo;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    private static final long serialVersionUID = 1L;
}