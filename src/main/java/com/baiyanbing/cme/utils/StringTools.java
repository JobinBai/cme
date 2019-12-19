package com.baiyanbing.cme.utils;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author baiyanbing
 * @createAt 2019/12/17 11:48
 */
public class StringTools {

    public static String remove(String str, String... removeStr){
        if(StrUtil.isEmpty(str)){
            return null;
        }
        for(String remove : removeStr){
            str = StrUtil.removeAll(str, remove);
        }
        return str;
    }
}
