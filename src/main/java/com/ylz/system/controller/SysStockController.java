package com.ylz.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import com.ylz.system.entity.SysParking;
import com.ylz.system.entity.SysStock;
import com.ylz.system.service.ISysStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 小区后勤的库存 前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/stock")
public class SysStockController {

    @Autowired
    private ISysStockService stockService;

    /**
     * 按条件查找物品信息，
     * @param param
     * @return
     */
    @RequestMapping("/search")
    public JsonPageResult searchBy(@RequestBody Map<String, Object> param){
        /* IPage<SysBuilding> pageList = buildingService.searchBy(param);*/
        IPage<SysStock> pageList = stockService.searchBy(param);
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
        int result = stockService.delete(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_DEL_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody SysStock sysStock){
        if (sysStock == null){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }

        /*LocalDateTime now = LocalDateTime.now();
        sysParking.setUpdateTime(now);*/
        LocalDateTime now = LocalDateTime.now();
        sysStock.setUpdateTime(now);
        int result = stockService.update(sysStock);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/add")
    public JsonResult addStock(@RequestBody SysStock sysStock){
        if (sysStock == null){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        LocalDateTime now = LocalDateTime.now();
        sysStock.setCreateTime(now);
        int result = stockService.add(sysStock);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_ADD_FAILED);
        }
        return ResultTool.success();
    }
}
