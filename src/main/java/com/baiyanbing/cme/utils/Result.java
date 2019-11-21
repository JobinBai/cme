package com.baiyanbing.cme.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author baiyanbing
 * @createAt 2019-11-18 14:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private String code;

    private String msg;

    private Object data;

    public static Result ok(Object data){
        return new Result("0000", "success", data);
    }

    public static Result fail(String code, String msg){
        return new Result(code, msg, null);
    }

    public static Result fail(String msg){
        return new Result("9999", msg, null);
    }
}
