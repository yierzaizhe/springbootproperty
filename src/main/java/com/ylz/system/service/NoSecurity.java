package com.ylz.system.service;

/**
 * @author ylz
 * @Description
 * @date 2021-05-27-00:33
 */

import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import org.springframework.stereotype.Service;

@Service
public class NoSecurity {

    public JsonResult quanxian(){
        return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
    }
}
