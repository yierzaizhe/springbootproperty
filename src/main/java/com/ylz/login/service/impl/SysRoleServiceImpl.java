package com.ylz.login.service.impl;

import com.ylz.login.entity.SysRole;
import com.ylz.login.mapper.SysRoleMapper;
import com.ylz.login.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-17
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Override
    public List<SysRole> getAll() {
        List<SysRole> list = roleMapper.selectList(null);

        return list;
    }
}
