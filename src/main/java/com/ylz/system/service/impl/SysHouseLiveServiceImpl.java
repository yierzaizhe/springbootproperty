package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysHouse;
import com.ylz.system.entity.SysHouseLive;
import com.ylz.system.mapper.SysHouseLiveMapper;
import com.ylz.system.mapper.SysHouseMapper;
import com.ylz.system.service.ISysHouseLiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Autowired SysHouseMapper houseMapper;

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
            if (!StringUtil.isEmpty((String) param.get("currentPage"))){
                currentPage =  Integer.parseInt((String) param.get("currentPage"));
            }
            if (!StringUtil.isEmpty((String) param.get("pageSize"))){
                pageSize =  Integer.parseInt((String) param.get("pageSize"));
            }if (!StringUtil.isEmpty((String) param.get("currentPage"))){
                currentPage =  Integer.parseInt((String) param.get("currentPage"));
            }
            if (!StringUtil.isEmpty((String) param.get("pageSize"))){
                pageSize =  Integer.parseInt((String) param.get("pageSize"));
            }
        }
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return houseLiveMapper.selectPage(page,wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(Map<String, Object> param) throws Exception {
        String houseCode = String.valueOf(param.get("houseCode"));
        int id = (int) param.get("id");
        SysHouse sysHouse = new SysHouse();
        QueryWrapper<SysHouse> wrapper = new QueryWrapper<>();
        wrapper.eq("house_code",houseCode);
        sysHouse.setIsLive(false);
        int result = houseMapper.update(sysHouse,wrapper);
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
    @Transactional(rollbackFor = Exception.class)
    public Integer add(SysHouseLive sysHouseLive) throws Exception {
        String houseCode = sysHouseLive.getHouseCode();
        QueryWrapper<SysHouse> wrapper = new QueryWrapper<>();
        wrapper.eq("house_code",houseCode);
        SysHouse sysHouse = new SysHouse();
        sysHouse.setIsLive(true);
        houseMapper.update(sysHouse,wrapper);
        return houseLiveMapper.insert(sysHouseLive);
    }



    @Override
    public int count(Map<String, Object> param) {
        QueryWrapper<SysHouseLive> wrapper = new QueryWrapper<>();
        if (param == null || StringUtil.isEmpty((String) param.get("houseCode")) ){
            return 0;
        }
        wrapper.eq("house_code",param.get("houseCode"));
        List list =houseLiveMapper.selectList(wrapper);
        return list.size();
    }
    @Override
    public List<Map<String, Object>> countLiveType() {
        QueryWrapper<SysHouseLive> wrapper = new QueryWrapper<>();
        wrapper.select("kind_param as name","COUNT(kind_param) AS value")
                .groupBy("kind_param");
        return houseLiveMapper.selectMaps(wrapper);
    }
}
