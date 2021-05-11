package com.ylz.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import com.ylz.system.entity.SysBuilding;
import com.ylz.system.service.ISysBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    /**
     * 按条件查找楼房信息，
     * @param param
     * @return
     */
    @RequestMapping("/search")
    public JsonPageResult searchBy(@RequestBody Map<String, Object> param){
       /* IPage<SysBuilding> pageList = buildingService.searchBy(param);*/
        IPage<SysBuilding> pageList = buildingService.searchBy(param);
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
        int result = buildingService.delete(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.BUILD_DEL_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody SysBuilding building){
        if (building == null){
            return ResultTool.fail(ResultCode.BUILD_UP_FAILED);
        }
        if (building.getId() == null ){
            int result = buildingService.add(building);
            if (result <= 0){
                return ResultTool.fail(ResultCode.BUILD_ADD_FAILED);
            }
            return ResultTool.success();
        }
        int result = buildingService.update(building);
        if (result <= 0){
            return ResultTool.fail(ResultCode.BUILD_UP_FAILED);
        }
        return ResultTool.success();
    }

}
