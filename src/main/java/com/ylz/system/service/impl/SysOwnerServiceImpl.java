package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysHouse;
import com.ylz.system.entity.SysHouseLive;
import com.ylz.system.entity.SysOwner;
import com.ylz.system.mapper.SysHouseMapper;
import com.ylz.system.mapper.SysOwnerMapper;
import com.ylz.system.service.ISysOwnerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 小区所有住户 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@Service
public class SysOwnerServiceImpl extends ServiceImpl<SysOwnerMapper, SysOwner> implements ISysOwnerService {

    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private SysOwnerMapper ownerMapper;
    @Override
    public IPage<SysOwner> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysOwner> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("houseCode"))){
                wrapper.like("house_code",param.get("houseCode"));
            }
            if (!StringUtil.isEmpty((String) param.get("name"))){
                wrapper.like("name",param.get("name"));
            }
            if (!StringUtil.isEmpty((String) param.get("type"))){
                wrapper.like("type",param.get("type"));
            }
            if (!StringUtil.isEmpty((String) param.get("startTime"))){
                wrapper.ge("create_time",param.get("startTime"));
            }
            if (!StringUtil.isEmpty((String) param.get("endTime"))){
                wrapper.le("create_time",param.get("endTime"));
            }
            if (!StringUtil.isEmpty((String) param.get("currentPage"))){
                currentPage =  Integer.parseInt((String) param.get("currentPage"));
            }
            if (!StringUtil.isEmpty((String) param.get("pageSize"))){
                pageSize =  Integer.parseInt((String) param.get("pageSize"));
            }
        }
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return ownerMapper.selectPage(page,wrapper);
    }


    @Override
    public Integer delete(Map<String, Object> param) {
        int id = (int) param.get("id");
        return ownerMapper.deleteById(id);
    }

    @Override
    public SysOwner findById(Integer id) {
        return ownerMapper.selectById(id);
    }

    @Override
    public Integer update(SysOwner sysOwner) {
        Integer result=0;
        if (sysOwner != null){
            QueryWrapper<SysOwner> wrapper = new QueryWrapper<>();
            wrapper.eq("id",sysOwner.getId());
            result = ownerMapper.update(sysOwner,wrapper);
        }
        return result;
    }

    @Override
    public Integer add(SysOwner sysOwner) {
        return ownerMapper.insert(sysOwner);
    }
}
