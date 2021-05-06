package com.ylz.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.utils.ResultTool;
import com.ylz.system.entity.SysBuilding;
import com.ylz.system.entity.SysCommunity;
import com.ylz.system.service.ISysBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 小区的楼栋 前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/building")
public class SysBuildingController {

    @Autowired
    private ISysBuildingService buildingService;

    @RequestMapping("/getAll")
    public JsonResult getAll(){
        /*IPage<SysBuilding> pageList = buildingService.getAll();*/
        //return ResultTool.successPage(pageList.getRecords(),pageList.getCurrent(),pageList.getPages(),pageList.getTotal());
        return ResultTool.success(buildingService.getAll());
    }

}
