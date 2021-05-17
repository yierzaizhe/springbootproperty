package com.ylz.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.login.entity.SysUser;
import com.ylz.login.entity.SysUserRoleRelation;
import com.ylz.login.entity.UserInfo;
import com.ylz.login.mapper.SysUserMapper;
import com.ylz.login.service.ISysUserRoleRelationService;
import com.ylz.login.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylz.system.entity.SysBuilding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-03-02
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ISysUserRoleRelationService relationService;
    @Override
    public IPage<SysUser> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("startTime"))){
                wrapper.ge("create_time",param.get("startTime"));
            }
            if (!StringUtil.isEmpty((String) param.get("endTime"))){
                wrapper.le("create_time",param.get("endTime"));
            }
            if (!StringUtil.isEmpty((String) param.get("account"))){
                wrapper.like("account",param.get("account"));
            }
            if (!StringUtil.isEmpty((String) param.get("userName"))){
                wrapper.like("user_name",param.get("userName"));
            }
            if (!StringUtil.isEmpty((String) param.get("currentPage"))){
                currentPage =  Integer.parseInt((String) param.get("currentPage"));
            }
            if (!StringUtil.isEmpty((String) param.get("pageSize"))){
                pageSize =  Integer.parseInt((String) param.get("pageSize"));
            }
        }
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        IPage<SysUser> userIPage = sysUserMapper.selectPage(page,wrapper);
        userIPage.getRecords().forEach(sysUser -> {

        });
        return sysUserMapper.selectPage(page,wrapper);
    }

    @Override
    public IPage<UserInfo> searchAll(Map<String, Object> param) {
        //创建QueryWrapper搜索对象，判断参数不为空则传入参数
        QueryWrapper<Map<String, Object>> wrapper = new QueryWrapper<>();
        if (!StringUtil.isEmpty((String) param.get("account"))){
            wrapper.like("account",param.get("account"));
        }
        if (!StringUtil.isEmpty((String) param.get("userName"))){
            wrapper.like("user_name",param.get("userName"));
        }
        if (!StringUtil.isEmpty((String) param.get("currentPage"))){
            currentPage =  Integer.parseInt((String) param.get("currentPage"));
        }
        if (!StringUtil.isEmpty((String) param.get("pageSize"))){
            pageSize =  Integer.parseInt((String) param.get("pageSize"));
        }
        //创建分页对象
        Page<Map<String, Object>> page = new Page<>(currentPage, pageSize);

        return sysUserMapper.searchUserInfo(page, wrapper);

    }


    @Override
    public Integer delete(Map<String, Object> param) {
        int id = (int) param.get("id");
        return sysUserMapper.deleteById(id);
    }

    @Override
    public SysBuilding findById(Integer id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update_two(Map<String, Object> param)  throws Exception {
        Integer result=0;

        String username = (String) param.get("user_name");
        String account = (String) param.get("account");
        /*String password = (String) param.get("password");*/
        Integer roleId = Integer.parseInt((String) param.get("role"));
        Integer userId = Integer.parseInt((String) param.get("id"));

        SysUserRoleRelation roleRelation = new SysUserRoleRelation();
        roleRelation.setRoleId(roleId);
        roleRelation.setUserId(userId);
        relationService.update(roleRelation);

        SysUser sysUser =new SysUser();
        sysUser.setId(userId);
        sysUser.setAccount(account);
        sysUser.setUserName(username);
        LocalDateTime now = LocalDateTime.now();
        sysUser.setUpdateTime(now);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",sysUser.getId());
        result = sysUserMapper.update(sysUser,wrapper);

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(@RequestBody Map<String, Object> param) throws Exception{
        String username = (String) param.get("userName");
        String account = (String) param.get("account");
        String password = (String) param.get("password");
        Integer role =  Integer.parseInt(String.valueOf(param.get("role")));
        SysUser sysUser = new SysUser();
        sysUser.setAccount(account);
        sysUser.setUserName(username);
        sysUser.setPassword(passwordEncoder.encode(password));
        LocalDateTime now = LocalDateTime.now();
        sysUser.setCreateTime(now);
        sysUserMapper.insert(sysUser);

        SysUser result=this.queryById(sysUser.getId());
        SysUserRoleRelation roleRelation = new SysUserRoleRelation();
        roleRelation.setRoleId(role);
        roleRelation.setUserId(result.getId());
        int result2= relationService.add(roleRelation);

        return result2;
    }


    @Override
    public SysUser queryById(Integer id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public SysUser update(SysUser sysUser) {
        sysUserMapper.updateById(sysUser);
        return this.queryById(sysUser.getId());
    }

    @Override
    public int restPassword(SysUser sysUser) {
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public SysUser selectByName(String userName) {
        return sysUserMapper.selectByName(userName);

    }

    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserMapper.queryAllByLimit(offset, limit);
    }
}
