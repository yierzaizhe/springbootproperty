package com.ylz.system.controller;


import afu.org.checkerframework.checker.oigj.qual.O;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import com.ylz.system.entity.SysParking;
import com.ylz.system.entity.SysParkingUse;
import com.ylz.system.service.ISysParkingService;
import com.ylz.system.service.ISysParkingUseService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/parking-use")
public class SysParkingUseController {
    @Autowired
    private ISysParkingUseService parkingUseService;

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
        IPage<SysParkingUse> pageList = parkingUseService.searchBy(param);
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
        int result = parkingUseService.delete(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_DEL_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody SysParkingUse sysParkingUse){
        if (sysParkingUse == null){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }

        /*LocalDateTime now = LocalDateTime.now();
        sysParking.setUpdateTime(now);*/
        LocalDateTime now = LocalDateTime.now();
        sysParkingUse.setUpdateTime(now);
        int result = parkingUseService.update(sysParkingUse);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        return ResultTool.success();
    }
    @RequestMapping("/add")
    public JsonResult addHouse(@RequestBody SysParkingUse sysParkingUse){
        if (sysParkingUse == null){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code",sysParkingUse.getParkingCode());
        if (parkingService.count(map)<=0){
            return ResultTool.fail(ResultCode.PARK_NOT_FAILED);
        }
        int result = parkingUseService.add(sysParkingUse);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_ADD_FAILED);
        }
        return ResultTool.success();
    }
}
