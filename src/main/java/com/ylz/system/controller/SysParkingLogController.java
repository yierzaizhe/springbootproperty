package com.ylz.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.IdGenerator;
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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
        sysParkingLog.setPayRemark(IdGenerator.getId());
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
    @RequestMapping("/toPay")
    public JsonResult toPay(@RequestBody Map<String, Object> param){
        if (param == null || StringUtil.isEmpty((String) param.get("carCard"))){
            return ResultTool.fail(ResultCode.PARK_UP_FAILED);
        }
        float pay = 0;
        String timeRange = "";
        String startTime = String.valueOf(param.get("startTime"));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime =formatter.format(date);
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date start = df.parse(startTime);
            java.util.Date end  = df.parse(endTime);
            long l=end.getTime()-start.getTime();
            long day=l/(24*60*60*1000);
            long hour=(l/(60*60*1000)-day*24);
            long min=((l/(60*1000))-day*24*60-hour*60);
            long s=(l/1000-day*24*60*60-hour*60*60-min*60);
            /*
            * 一天 40元
            *一个小时2元半个小时以下免费
            * */
            timeRange=""+day+"天"+hour+"小时"+min+"分";
            if (day>0){
                pay = 40 * day;
            }else if(day == 0){
                pay = pay + 2*hour;
            }else if (min>30){
                pay = pay +2;
            }else {
                pay=0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultTool.fail();
        }
        if (StringUtil.isEmpty((String) param.get("havePark"))){
            String havePark = (String) param.get("havePark");
            if (havePark.equals("0")){
                pay = 0;
            }
        }
        SysParkingLog sysParkingLog =new SysParkingLog();
        sysParkingLog.setId((Integer) param.get("id"));
        LocalDateTime now = LocalDateTime.now();
        sysParkingLog.setEndTime(now);

        sysParkingLog.setPay(String.valueOf(pay));
        sysParkingLog.setFinish("1");
        int result =parkingLogService.update(sysParkingLog);
        if (result <0 ){
            return ResultTool.fail();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("carCard",param.get("carCard"));
        map.put("endTime",endTime);
        map.put("timeRange",timeRange);
        map.put("totalFee",pay);
        map.put("havePark",param.get("havePark"));
        return ResultTool.success(map);
    }

}
