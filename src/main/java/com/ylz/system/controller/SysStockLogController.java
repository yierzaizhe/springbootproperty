package com.ylz.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysStock;
import com.ylz.system.entity.SysStockLog;
import com.ylz.system.service.ISysStockLogService;
import com.ylz.system.service.ISysStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 小区后勤的库存采购日志 前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-05-15
 */
@RestController
@RequestMapping("/stock-log")
public class SysStockLogController {
    @Autowired
    private ISysStockLogService stockLogService;

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
        IPage<SysStockLog> pageList = stockLogService.searchBy(param);
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
        int result = stockLogService.delete(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_DEL_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody SysStockLog sysStock){
        if (sysStock == null){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        LocalDateTime now = LocalDateTime.now();
        sysStock.setUpdateTime(now);
        int result = stockLogService.update(sysStock);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addStockLog(@RequestBody Map<String, Object> param){
        if (param == null){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        LocalDateTime now = LocalDateTime.now();
        SysStock stock = new SysStock();
        Integer goodId = (Integer) param.get("goodsId");
        if (goodId != null){
            stock.setId(goodId);
        }else {
            return ResultTool.fail(ResultCode.PARK_ADD_FAILED);
        }
        int totalSum = (Integer) param.get("sum");
        stock.setTotal(totalSum);

        stock.setUpdateTime(now);
        int result = 0;
        try {
            int result2 = stockService.update(stock);
            if (result2 <= 0){
                return ResultTool.fail(ResultCode.PARK_ADD_FAILED);
            }
            SysStockLog stockLog =new SysStockLog();
            if (!StringUtil.isEmpty((String) param.get("goods"))){
                stockLog.setGoods((String) param.get("goods"));
            }
            stockLog.setTotal((int) param.get("total"));
            if (!StringUtil.isEmpty((String) param.get("username"))){
                stockLog.setUsername((String) param.get("username"));
            }
            if (!StringUtil.isEmpty((String) param.get("totalFee"))){
                stockLog.setTotalFee((String) param.get("totalFee"));
            }
            if (!StringUtil.isEmpty((String) param.get("logRemark"))){
                stockLog.setLogRemark((String) param.get("logRemark"));
            }
            stockLog.setCreateTime(now);
            result = stockLogService.add(stockLog);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_ADD_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/updateStatus/{status}/{id}")
    public JsonResult updateStatus(@PathVariable("status") String status, @PathVariable("id") Integer id){
        Boolean flag = stockLogService.updateStatus(status,id);
        SysStockLog stockLog = new SysStockLog();
        LocalDateTime now = LocalDateTime.now();
        stockLog.setUpdateTime(now);
        stockLog.setId(id);
        stockLogService.update(stockLog);
        if (flag){
            return ResultTool.success();
        }else {
            return ResultTool.fail(ResultCode.COMMUNITY_UPSTA_FAILED);
        }
    }
    @RequestMapping("/updateStatus")
    public JsonResult updateStatus(@RequestBody Map<String, Object> param){
        if (param == null){
            return ResultTool.fail();
        }
        Integer id = Integer.parseInt(String.valueOf(param.get("id")));
        String status = String.valueOf(param.get("status"));
        Boolean flag = stockLogService.updateStatus(status,id);
        SysStockLog stockLog = new SysStockLog();
        LocalDateTime now = LocalDateTime.now();
        stockLog.setUpdateTime(now);
        stockLog.setId(id);
        stockLogService.update(stockLog);
        if (flag){
            return ResultTool.success();
        }else {
            return ResultTool.fail(ResultCode.COMMUNITY_UPSTA_FAILED);
        }
    }
}
