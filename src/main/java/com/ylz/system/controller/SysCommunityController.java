package com.ylz.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.ylz.common.entity.JsonPageResult;

import com.ylz.system.service.ISysCommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 小区表 前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-03-29
 */
@RestController
@RequestMapping("/community")
@Slf4j
public class SysCommunityController {

    @Autowired
    private ISysCommunityService sysCommunityService;

    @RequestMapping("/getAll")
    public JsonPageResult getAll(){
        JsonPageResult result =new JsonPageResult();
        result.setData(sysCommunityService.findAll());
        result.setTotal((long) sysCommunityService.findAll().size());
        return result;
    }


}
