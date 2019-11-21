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
 * @createAt 2019-11-18 14:47
 */

@Data
@TableName(value = "file_source")
public class FileSource implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 文件类型:后缀名
     */
    @TableField(value = "type")
    private String type;

    /**
     * 分类id
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    private static final long serialVersionUID = 1L;
}