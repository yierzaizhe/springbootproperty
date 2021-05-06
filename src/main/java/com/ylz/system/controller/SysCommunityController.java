package com.ylz.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import com.ylz.login.service.ISysUserService;
import com.ylz.system.entity.SysCommunity;
import com.ylz.system.service.ISysCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区表 前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-03-31
 */
@RestController
@RequestMapping("/community")
public class SysCommunityController {

    @Autowired
    private ISysCommunityService communityService;

    /*@RequestMapping("/getAll")
    public JsonPageResult getAll2(){
        return ResultTool.successPage(communityService.getAll().getRecords(), communityService.getAll().getTotal());
    }*/
    /**
     * 按条件查找小区信息，
     * @param param
     * @return
     */
    @RequestMapping("/search")
    public JsonPageResult searchBy(@RequestBody Map<String, Object> param){
        IPage<SysCommunity> pageList = communityService.searchBy(param);
        return ResultTool.successPage(pageList.getRecords(),pageList.getCurrent(),pageList.getPages(),pageList.getTotal());
    }

    @RequestMapping("/add")
    public JsonResult add(@RequestBody SysCommunity sysCommunity){
        if (sysCommunity == null){
            return ResultTool.fail(ResultCode.COMMUNITY_ADD_FAILED);
        }
        int result = communityService.add(sysCommunity);
        if (result <= 0){
            return ResultTool.fail(ResultCode.COMMUNITY_ADD_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/delete")
    public JsonResult delete(@RequestBody List<Integer> idList){
        if (idList.size()==0){
            return ResultTool.fail(ResultCode.COMMUNITY_DEL_FAILED);
        }
        int result = communityService.delete(idList);
        if (result <= 0){
            return ResultTool.fail(ResultCode.COMMUNITY_DEL_FAILED);
        }
        return ResultTool.success();
    }
    @RequestMapping("/update")
    public JsonResult update(@RequestBody SysCommunity community){
        if (community == null){
            return ResultTool.fail(ResultCode.COMMUNITY_UP_FAILED);
        }
        int result = communityService.update(community);
        if (result <= 0){
            return ResultTool.fail(ResultCode.COMMUNITY_UP_FAILED);
        }
        return ResultTool.success();
    }
    @RequestMapping("/updateStatus/{status}/{id}")
    public JsonResult updateStatus(@PathVariable("status") String status, @PathVariable("id") Integer id){
        Boolean flag = communityService.updateStatus(status,id);
        if (flag){
            return ResultTool.success();
        }else {
            return ResultTool.fail(ResultCode.COMMUNITY_UPSTA_FAILED);
        }
    }


}
