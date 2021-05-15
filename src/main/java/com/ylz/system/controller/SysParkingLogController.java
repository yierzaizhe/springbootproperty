package com.ylz.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysParkingLog;
import com.ylz.system.entity.SysParkingUse;
import com.ylz.system.service.ISysParkingLogService;
import com.ylz.system.service.ISysParkingUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
@RequestMapping("/parking-log")
public class SysParkingLogController {

    @Autowired
    private ISysParkingUseService useService;

    @Autowired
    private ISysParkingLogService parkingLogService;

    /**
     * 按条件查找停车日志信息，
     * @param param
     * @return
     */
    @RequestMapping("/search")
    public JsonPageResult searchBy(@RequestBody Map<String, Object> param){
        /* IPage<SysBuilding> pageList = buildingService.searchBy(param);*/
        IPage<SysParkingLog> pageList = parkingLogService.searchBy(param);
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
        int result = parkingLogService.delete(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_DEL_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody SysParkingLog sysParkingLog){
        if (sysParkingLog == null){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        if (sysParkingLog.getEndTime() == null){
            LocalDateTime now = LocalDateTime.now();
            sysParkingLog.setEndTime(now);
        }
        int result = parkingLogService.update(sysParkingLog);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        return ResultTool.success();
    }
    @RequestMapping("/add")
    public JsonResult addParkingPay(@RequestBody Map<String, Object> param){
        if (param == null || StringUtil.isEmpty((String) param.get("carCard"))){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        IPage<SysParkingLog> parkingLogIPage = parkingLogService.searchBy(param);
        if (parkingLogIPage.getTotal() > 0 ){
            return ResultTool.fail(ResultCode.PAY_ADD_FAILED);
        }
        SysParkingLog sysParkingLog =new SysParkingLog();
        sysParkingLog.setCarCard(String.valueOf(param.get("carCard")));
        sysParkingLog.setPayRemark(String.valueOf(param.get("payRemark")));
        LocalDateTime now = LocalDateTime.now();
        sysParkingLog.setStartTime(now);
        IPage<SysParkingUse> info = useService.searchBy(param);
        if (info.getTotal() >0 ){
            sysParkingLog.setHavePark("0");
        }else {
            sysParkingLog.setHavePark("1");
        }
        int result = parkingLogService.add(sysParkingLog);
        if (result <= 0){
            return ResultTool.fail(ResultCode.PARK_ADD_FAILED);
        }
        return ResultTool.success();
    }
}
