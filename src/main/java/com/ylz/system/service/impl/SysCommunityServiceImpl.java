package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysCommunity;
import com.ylz.system.mapper.SysCommunityMapper;
import com.ylz.system.service.ISysCommunityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区表 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-03-31
 */
@Service
public class SysCommunityServiceImpl extends ServiceImpl<SysCommunityMapper, SysCommunity> implements ISysCommunityService {
    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private SysCommunityMapper communityMapper;
    @Override
    public IPage<SysCommunity> getAll() {
        return communityMapper.selectPage(new Page<SysCommunity>(1,2),null);
    }

    /**
     * 按条件查找小区信息，
     * @param param
     * @return
     */
    @Override
    public IPage<SysCommunity> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysCommunity> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("startTime"))){
                wrapper.ge("create_time",param.get("startTime"));
            }
            if (!StringUtil.isEmpty((String) param.get("endTime"))){
                wrapper.le("create_time",param.get("endTime"));
            }
            if (!StringUtil.isEmpty((String) param.get("name"))){
                wrapper.like("name",param.get("name"));
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
        return communityMapper.selectMapsPage(page,wrapper);
    }

    /**
     * 通过id查找小区
     * @param id
     * @return
     */
    @Override
    public SysCommunity findById(Integer id) {
        return communityMapper.selectById(id);
    }

    /**
     * 增加
     * @param sysCommunity
     * @return
     */
    @Override
    public Integer add(SysCommunity sysCommunity) {
        return communityMapper.insert(sysCommunity);
    }

    @Override
    public Integer delete(List<Integer> idList) {
        return communityMapper.deleteBatchIds(idList);
    }

    @Override
    public Integer update(SysCommunity sysCommunity) {
        Integer result=0;
        if (sysCommunity != null){
            QueryWrapper<SysCommunity> wrapper = new QueryWrapper<>();
            wrapper.eq("id",sysCommunity.getId());
            result = communityMapper.update(sysCommunity,wrapper);
        }
        return result;
    }

    @Override
    public Boolean updateStatus(String status, Integer id) {
        SysCommunity community = new SysCommunity();
        community.setStatus(status);
        //community.setId(id);
        QueryWrapper<SysCommunity> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        int row = communityMapper.update(community,wrapper);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }


}
