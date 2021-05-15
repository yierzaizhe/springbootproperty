package com.ylz.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import com.ylz.system.entity.SysHouse;
import com.ylz.system.entity.SysParking;
import com.ylz.system.service.ISysHouseService;
import com.ylz.system.service.ISysParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 车位表 前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/parking")
public class SysParkingController {
    @Autowired
    private ISysParkingService parkingService;

    /**
     * 按条件查找车位信息，
     * @param param
     * @return
     */
    @RequestMapping("/search")
    public JsonPageResult searchBy(@RequestBody Map<String, Object> param){
        /* IPage<SysBuilding> pageList = buildingService.searchBy(param);*/
        IPage<SysParking> pageList = parkingService.searchBy(param);
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
            return ResultTool.fail(ResultCode.PARK_DEL_FAILED);
        }
        int result = parkingService.delete(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_DEL_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody SysParking sysParking){
        if (sysParking == null){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }

        /*LocalDateTime now = LocalDateTime.now();
        sysParking.setUpdateTime(now);*/
        LocalDateTime now = LocalDateTime.now();
        sysParking.setUpdateTime(now);
        int result = parkingService.update(sysParking);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        return ResultTool.success();
    }
    @RequestMapping("/add")
    public JsonResult addHouse(@RequestBody SysParking sysParking){
        if (sysParking == null){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        int result = parkingService.add(sysParking);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_ADD_FAILED);
        }
        return ResultTool.success();
    }
}
