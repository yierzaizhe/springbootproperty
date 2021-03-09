package com.ylz.login.service;

import com.ylz.login.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-03-02
 */
public interface ISysPermissionService extends IService<SysPermission> {
    /**
     * 查询用户的权限列表
     *
     * @param userId
     * @return
     */
    List<SysPermission> selectListByUser(Integer userId);
    /**
     * 查询具体某个接口的权限
     *
     * @param path
     * @return
     */
    List<SysPermission> selectListByPath(String path);
}
