package com.ylz.system.controller;

import com.ylz.common.entity.JsonResult;
import com.ylz.common.utils.ResultTool;
import com.ylz.login.entity.SysUser;
import com.ylz.system.service.ISysHouseLiveService;
import com.ylz.system.service.ISysHouseService;
import com.ylz.system.service.ISysParkingUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ylz
 * @Description 数据可视化
 * @date 2021-05-17-23:52
 */
@RestController
@RequestMapping("/count")
public class SysCountController {
    @Autowired
    private ISysHouseService houseService;
    @Autowired
    private ISysHouseLiveService houseLiveService;
    @Autowired
    private ISysParkingUseService parkingUseService;


    @GetMapping("/buildPeople")
    public JsonResult buildPeople(){
        List<Map<String, Object>> map1=houseService.countBuildPeople();
        List map2 = new ArrayList<>();
        List map3 = new ArrayList<>();
        for (Map<String, Object> map :map1) {
            /*Map<String, Object> building_name= new HashMap<>();
            building_name.put("building_name",map.get("building_name"));
            Map<String, Object> count= new HashMap<>();
            count.put("count",map.get("count"));*/
            map2.add(map.get("building_name"));
            map3.add(map.get("count"));
            }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("building_name",map2);
        resultMap.put("count",map3);
        return ResultTool.success(resultMap);
    }

    @GetMapping("/countLiveType")
    public JsonResult countLiveType(){
        List<Map<String, Object>> map1=houseLiveService.countLiveType();

        return ResultTool.success(map1);
    }

    @GetMapping("/countParkType")
    public JsonResult countParkType(){
        List<Map<String, Object>> map1=parkingUseService.countParkType();
        List<Map<String, Object>> map2=new ArrayList<>();
        for (Map<String, Object> item: map1) {
            if (item.get("name").equals("0")){
                Map<String, Object> map =new HashMap<>();
                map.put("name","购买");
                map.put("value",item.get("value"));
                map2.add(map);
            }
            if (item.get("name").equals("1")){
                Map<String, Object> map =new HashMap<>();
                map.put("name","月租");
                map.put("value",item.get("value"));
                map2.add(map);
            }
            if (item.get("name").equals("2")){
                Map<String, Object> map =new HashMap<>();
                map.put("name","年租");
                map.put("value",item.get("value"));
                map2.add(map);
            }
        }
        return ResultTool.success(map2);
    }
}
