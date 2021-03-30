package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.system.entity.SysCommunity;
import com.ylz.system.mapper.SysCommunityMapper;
import com.ylz.system.service.ISysCommunityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区表 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-03-29
 */
@Service
public class SysCommunityServiceImpl extends ServiceImpl<SysCommunityMapper, SysCommunity> implements ISysCommunityService {

    @Autowired
    private SysCommunityMapper sysCommunityMapper;

    @Override
    public List<SysCommunity> findAll() {
        return sysCommunityMapper.selectList(null);
    }


}
