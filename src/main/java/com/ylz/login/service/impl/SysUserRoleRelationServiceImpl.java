package com.ylz.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ylz.login.entity.SysUserRoleRelation;
import com.ylz.login.mapper.SysUserRoleRelationMapper;
import com.ylz.login.service.ISysUserRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户角色关联关系表 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-17
 */
@Service
public class SysUserRoleRelationServiceImpl extends ServiceImpl<SysUserRoleRelationMapper, SysUserRoleRelation> implements ISysUserRoleRelationService {

    @Autowired
    private SysUserRoleRelationMapper roleRelationMapper;

    @Override
    public Integer update(SysUserRoleRelation roleRelation) {
        QueryWrapper<SysUserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",roleRelation.getUserId());

        if (roleRelationMapper.selectCount(wrapper)<=0){
            return roleRelationMapper.insert(roleRelation);
        }
        return roleRelationMapper.update(roleRelation,wrapper);
    }

    @Override
    public Integer add(SysUserRoleRelation roleRelation) {

        return roleRelationMapper.insert(roleRelation);
    }
}
