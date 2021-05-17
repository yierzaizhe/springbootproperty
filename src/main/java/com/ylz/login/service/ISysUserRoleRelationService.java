package com.ylz.login.service;

import com.ylz.login.entity.SysUser;
import com.ylz.login.entity.SysUserRoleRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色关联关系表 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-05-17
 */
public interface ISysUserRoleRelationService extends IService<SysUserRoleRelation> {
    //更新信息
    public Integer update(SysUserRoleRelation roleRelation);

    public Integer add(SysUserRoleRelation roleRelation);
}
