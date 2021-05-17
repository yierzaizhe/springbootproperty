package com.ylz.login.service;

import com.ylz.login.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ylz.login.entity.SysUserRoleRelation;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-05-17
 */
public interface ISysRoleService extends IService<SysRole> {

    //更新信息
    public List<SysRole> getAll();
}
