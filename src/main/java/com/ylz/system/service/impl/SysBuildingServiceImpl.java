package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.system.entity.SysBuilding;
import com.ylz.system.entity.SysCommunity;
import com.ylz.system.mapper.SysBuildingMapper;
import com.ylz.system.service.ISysBuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区的楼栋 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-06
 */
@Service
public class SysBuildingServiceImpl extends ServiceImpl<SysBuildingMapper, SysBuilding> implements ISysBuildingService {
    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private SysBuildingMapper buildingMapper;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<SysBuilding> getAll() {
        //return buildingMapper.selectPage(null,null);
        return buildingMapper.selectList(null);
    }

    /**
     * 按条件搜索
     * @param param
     * @return
     */
    @Override
    public IPage<SysBuilding> searchBy(Map<String, Object> param) {
        return null;
    }
}
