package com.ylz.login.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.common.entity.JsonPageResult;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import com.ylz.common.utils.StringUtil;
import com.ylz.login.entity.SysRole;
import com.ylz.login.entity.SysUser;
import com.ylz.login.entity.SysUserRoleRelation;
import com.ylz.login.entity.UserInfo;
import com.ylz.login.service.ISysRoleService;
import com.ylz.login.service.ISysUserRoleRelationService;
import com.ylz.login.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ylz
 * @since 2021-03-03
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserRoleRelationService relationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getUser")
    public JsonResult users(){
        //List<SysUser> users = userService.queryAllByLimit(1, 100);
        List<SysUser> users = userService.list();

        return ResultTool.success(users);
    }
    /**
     * 按条件查找楼房信息，
     * @param param
     * @return
     */
    @RequestMapping("/search")
    public JsonPageResult searchBy(@RequestBody Map<String, Object> param){
        IPage<SysUser> pageList = userService.searchBy(param);
        return ResultTool.successPage(pageList.getRecords(),pageList.getCurrent(),pageList.getPages(),pageList.getTotal());
    }

    @RequestMapping("/searchAll")
    public JsonPageResult searchAll(@RequestBody Map<String, Object> param){
        IPage<UserInfo> pageList = userService.searchAll(param);
        /*pageList.getRecords().forEach(map -> {
            String password=passwordEncoder.encode((CharSequence) map.get("password"));
            map.put("password",password);
        });*/
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
        int result = userService.delete(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.BUILD_DEL_FAILED);
        }
        return ResultTool.success();
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody Map<String, Object> param) throws Exception {
        if (param == null){
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        int result = userService.update_two(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        return ResultTool.success();
    }

    @RequestMapping("/restPassword")
    public JsonResult restPassword(@RequestBody SysUser sysUser) throws Exception {
        if (sysUser == null){
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        sysUser.setPassword(passwordEncoder.encode("111111"));
        LocalDateTime now = LocalDateTime.now();
        sysUser.setUpdateTime(now);
        int result = userService.restPassword(sysUser);
        if (result <= 0){
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        return ResultTool.success();
    }
    @RequestMapping("/updatePass")
    public JsonResult updatePass(@RequestBody Map<String, Object> param) throws Exception {
        if (param == null){
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        String account = (String) param.get("account");
        String oldPassword = (String) param.get("oldPassword");
        String newPassword = (String) param.get("newPassword");
        IPage<SysUser> oldUser = userService.searchBy(param);
        if (oldUser.getTotal() >1 || oldUser.getTotal() <=0){
            return ResultTool.fail();
        }
        String password = oldUser.getRecords().get(0).getPassword();
        boolean passResult=passwordEncoder.matches(oldPassword,password);
        if (passResult==false){
            return ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
        }
        SysUser sysUser = new SysUser();
        sysUser.setAccount(account);
        sysUser.setPassword(passwordEncoder.encode(newPassword));
        LocalDateTime now = LocalDateTime.now();
        sysUser.setUpdateTime(now);
        int result = userService.updatePassword(sysUser);
        if (result <= 0){
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        return ResultTool.success();
    }
    @RequestMapping("/add")
    public JsonResult add(@RequestBody Map<String, Object> param) throws Exception {
        if (param == null){
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        String username = (String) param.get("userName");
        String account = (String) param.get("account");
        String password = (String) param.get("password");
        String role = (String) param.get("role");

        if (StringUtil.isEmpty(username)||StringUtil.isEmpty(password)||StringUtil.isEmpty(role)){
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        IPage<SysUser> pageList = userService.searchBy(param);
        if (pageList.getTotal()>0){
            return ResultTool.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        Integer result = userService.add(param);
        if (result <=0){
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        return ResultTool.success();
    }

    @RequestMapping("/add-role")
    public JsonResult addRole(@RequestBody SysUserRoleRelation roleRelation){
        if (roleRelation == null){
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        int result = relationService.add(roleRelation);
        if (result <= 0){
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        return ResultTool.success();
    }

    @RequestMapping("/updateState")
    public JsonResult updateState(@RequestBody Map<String, Object> param){
        if (param == null){
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }

        int result = userService.updateState(param);
        if (result <= 0){
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        return ResultTool.success();
    }
    @GetMapping("/getRole")
    public JsonResult getRole(){

        //List<SysUser> users = userService.list();
        List<SysRole> result = roleService.getAll();
        List<Map<String, String>> mapList = new ArrayList<>();
        for (SysRole role : result) {
            Map map =new HashMap();
            if (role.getId() == 1){
                continue;
            }
            map.put("value",role.getId()+"");
            map.put("label",role.getRoleName());
            mapList.add(map);
        }
        return ResultTool.success(mapList);
    }


}
