package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysHouse;
import com.ylz.system.entity.SysHouseLive;
import com.ylz.system.mapper.SysHouseLiveMapper;
import com.ylz.system.service.ISysHouseLiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 房屋居住情况 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@Service
public class SysHouseLiveServiceImpl extends ServiceImpl<SysHouseLiveMapper, SysHouseLive> implements ISysHouseLiveService {
    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private SysHouseLiveMapper houseLiveMapper;

    @Override
    public IPage<SysHouseLive> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysHouseLive> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("houseCode"))){
                wrapper.like("house_code",param.get("houseCode"));
            }
            if (!StringUtil.isEmpty((String) param.get("kind"))){
                wrapper.eq("kind",param.get("kind"));
            }
            if (!StringUtil.isEmpty((String) param.get("startTime"))){
                wrapper.ge("start_time",param.get("startTime"));
            }
            if (!StringUtil.isEmpty((String) param.get("endTime"))){
                wrapper.le("start_time",param.get("endTime"));
            }
        }
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return houseLiveMapper.selectPage(page,wrapper);
    }

    @Override
    public Integer delete(Map<String, Object> param) {

        int id = (int) param.get("id");
        return houseLiveMapper.deleteById(id);
    }

    @Override
    public SysHouseLive findById(Integer id) {
        return houseLiveMapper.selectById(id);
    }

    @Override
    public Integer update(SysHouseLive sysHouseLive){
        Integer result=0;
        if (sysHouseLive != null){
            QueryWrapper<SysHouseLive> wrapper = new QueryWrapper<>();
            wrapper.eq("id",sysHouseLive.getId());
            result = houseLiveMapper.update(sysHouseLive,wrapper);
        }
        return result;
    }

    @Override
    public Integer add(SysHouseLive sysHouseLive) {
        return houseLiveMapper.insert(sysHouseLive);
    }
}
