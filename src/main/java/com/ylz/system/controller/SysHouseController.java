package com.ylz.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import com.ylz.system.entity.SysBuilding;
import com.ylz.system.entity.SysHouse;
import com.ylz.system.service.ISysHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 房屋信息 前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/house")
public class SysHouseController {

    @Autowired
    private ISysHouseService houseService;

    /**
     * 按条件查找楼房信息，
     * @param param
     * @return
     */
    @RequestMapping("/search")
    public JsonPageResult searchBy(@RequestBody Map<String, Object> param){
        /* IPage<SysBuilding> pageList = buildingService.searchBy(param);*/
        IPage<SysHouse> pageList = houseService.searchBy(param);
        return ResultTool.successPage(pageList.getRecords(),pageList.getCurrent(),pageList.getPages(),pageList.getTotal());
    }

    /**
     * 根据id，删除相应的信息
     * @param param
     * @return
     */
    @RequestMapping("/delete")
    public JsonResult delete(@RequestBody Map<String, Object> param){
        if (param.size()<=0 || param == null || param.get("id") == null){
            return ResultTool.fail(ResultCode.BUILD_DEL_FAILED);
        }
        int result = houseService.delete(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.BUILD_DEL_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody SysHouse sysHouse){
        if (sysHouse == null){
            return ResultTool.fail(ResultCode.BUILD_UP_FAILED);
        }
        /*if (sysHouse.getId() == null ){
            int result = houseService.add(sysHouse);
            if (result <= 0){
                return ResultTool.fail(ResultCode.BUILD_ADD_FAILED);
            }
            return ResultTool.success();
        }*/
        int result = houseService.update(sysHouse);
        if (result <= 0){
            return ResultTool.fail(ResultCode.BUILD_UP_FAILED);
        }
        return ResultTool.success();
    }
    @RequestMapping("/add")
    public JsonResult addHouse(@RequestBody SysHouse sysHouse){
        if (sysHouse == null){
            return ResultTool.fail(ResultCode.BUILD_UP_FAILED);
        }
        int result = houseService.add(sysHouse);
        if (result <= 0){
            return ResultTool.fail(ResultCode.BUILD_ADD_FAILED);
        }
        return ResultTool.success();
    }
}


