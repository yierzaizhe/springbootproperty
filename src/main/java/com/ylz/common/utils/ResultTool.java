package com.ylz.common.utils;

import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;

/**
 * @author ylz
 * @date 2021-03-02-11:53
 */
public class ResultTool {
    public static JsonResult success(){
        return new JsonResult(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(true, data);
    }
    public static <T> JsonResult<T> success(String token) {
        return new JsonResult(true, token);
    }

    public static JsonResult fail() {
        return new JsonResult(false);
    }

    public static JsonResult fail(ResultCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }
}
