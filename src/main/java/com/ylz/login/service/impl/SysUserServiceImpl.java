package com.ylz.login.service.impl;

import com.ylz.login.entity.SysUser;
import com.ylz.login.mapper.SysUserMapper;
import com.ylz.login.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private SysUserMapper sysUserMapper;


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
    public SysUser selectByName(String userName) {
        /*QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ge("acount",userName);
        sysUserMapper.selectOne(queryWrapper);*/
        return sysUserMapper.selectByName(userName);

    }

    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserMapper.queryAllByLimit(offset, limit);
    }
}
