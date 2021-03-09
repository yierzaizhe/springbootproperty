package com.ylz.login.controller;


import com.ylz.common.entity.JsonResult;
import com.ylz.common.utils.ResultTool;
import com.ylz.login.entity.SysUser;
import com.ylz.login.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-03-03
 */
@RestController
public class SysUserController {
    @Autowired
    private ISysUserService userService;

    @GetMapping("/getUser")
    public JsonResult users(){
        //List<SysUser> users = userService.queryAllByLimit(1, 100);
        List<SysUser> users = userService.list();

        return ResultTool.success(users);
    }

}
