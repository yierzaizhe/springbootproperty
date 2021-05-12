package com.ylz.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import com.ylz.system.entity.SysHouseLive;
import com.ylz.system.entity.SysOwner;
import com.ylz.system.service.ISysHouseLiveService;
import com.ylz.system.service.ISysHouseService;
import com.ylz.system.service.ISysOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 房屋居住情况 前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/house-live")
public class SysHouseLiveController {
    @Autowired
    private ISysHouseLiveService houseLiveService;

    @Autowired
    private ISysHouseService houseService;

    @RequestMapping("/search")
    public JsonPageResult searchBy(@RequestBody Map<String, Object> param){
        /* IPage<SysBuilding> pageList = buildingService.searchBy(param);*/
        IPage<SysHouseLive> pageList = houseLiveService.searchBy(param);
        return ResultTool.successPage(pageList.getRecords(),pageList.getCurrent(),pageList.getPages(),pageList.getTotal());
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody SysHouseLive sysHouseLive){
        if (sysHouseLive == null){
            return ResultTool.fail(ResultCode.OWNER_UP_FAILED);
        }
        int result = houseLiveService.update(sysHouseLive);
        if (result <= 0){
            return ResultTool.fail(ResultCode.LIVE_UP_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/add")
    public JsonResult addOwner(@RequestBody SysHouseLive sysHouseLive){
        if (sysHouseLive == null){
            return ResultTool.fail(ResultCode.LIVE_ADD_FAILED);
        }
        Map<String, Object> param = new HashMap<>();
        param.put("houseCode",sysHouseLive.getHouseCode());
        if (houseService.count(param) <= 0){
            return ResultTool.fail(ResultCode.OWNER_ADD_HOUSE_FAILED);
        }
        if (houseLiveService.count(param) > 0){
            return ResultTool.fail(ResultCode.LIVE_EXIST_FAILED);
        }
        int result = houseLiveService.add(sysHouseLive);
        if (result <= 0){
            return ResultTool.fail(ResultCode.LIVE_ADD_FAILED);
        }
        return ResultTool.success();
    }

    /**
     * 根据id，删除相应的信息
     * @param param
     * @return
     */
    @RequestMapping("/delete")
    public JsonResult delete(@RequestBody Map<String, Object> param){
        if (param.size()<=0 || param == null || param.get("id") == null){
            return ResultTool.fail(ResultCode.LIVE_DEL_FAILED);
        }
        int result = houseLiveService.delete(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.LIVE_DEL_FAILED);
        }
        return ResultTool.success();
    }

}
