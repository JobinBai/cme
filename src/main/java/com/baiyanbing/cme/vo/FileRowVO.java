package com.baiyanbing.cme.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author baiyanbing
 * @createAt 2019-11-18 18:45
 */
@Getter
@Setter
@Accessors(fluent = true)
public class FileRowVO {

    private String fileName;

    private Integer rowNo;

    private String content;

}
