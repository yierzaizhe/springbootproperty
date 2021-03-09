package com.ylz.login.service.impl;

import com.ylz.login.entity.SysPermission;
import com.ylz.login.mapper.SysPermissionMapper;
import com.ylz.login.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-03-02
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;
    @Override
    public List<SysPermission> selectListByUser(Integer userId) {
        return permissionMapper.selectListByUser(userId);
    }

    @Override
    public List<SysPermission> selectListByPath(String path) {
        return permissionMapper.selectListByPath(path);
    }
}
