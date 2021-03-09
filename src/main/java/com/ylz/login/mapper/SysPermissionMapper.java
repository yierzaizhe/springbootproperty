package com.ylz.login.mapper;

import com.ylz.login.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author ylz
 * @since 2021-03-02
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> selectListByUser(Integer id);
    /**
     * 查询具体某个接口的权限
     *
     * @param path 接口路径
     * @return
     */
    List<SysPermission> selectListByPath(String path);
}
