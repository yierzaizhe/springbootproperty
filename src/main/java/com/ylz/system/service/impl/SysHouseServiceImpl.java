package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysBuilding;
import com.ylz.system.entity.SysHouse;
import com.ylz.system.mapper.SysHouseMapper;
import com.ylz.system.service.ISysHouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 房屋信息 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-06
 */
@Service
public class SysHouseServiceImpl extends ServiceImpl<SysHouseMapper, SysHouse> implements ISysHouseService {
    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private SysHouseMapper houseMapper;

    @Override
    public IPage<SysHouse> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysHouse> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("houseCode"))){
                wrapper.like("house_code",param.get("houseCode"));
            }
            if (!StringUtil.isEmpty((String) param.get("buildingName"))){
                wrapper.like("building_name",param.get("buildingName"));
            }
            if (!StringUtil.isEmpty((String) param.get("unit"))){
                wrapper.like("unit",param.get("unit"));
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
        return houseMapper.selectPage(page,wrapper);
    }

    @Override
    public int count(Map<String, Object> param) {
        QueryWrapper<SysHouse> wrapper = new QueryWrapper<>();
        if (param == null || StringUtil.isEmpty((String) param.get("houseCode")) ){
            return 0;
        }
        wrapper.eq("house_code",param.get("houseCode"));
        return houseMapper.selectCount(wrapper);
    }

    @Override
    public Integer delete(Map<String, Object> param) {
        int id = (int) param.get("id");
        return houseMapper.deleteById(id);
    }

    @Override
    public Integer deleteByHouseCode(Map<String, Object> param) {
        String code= String.valueOf(param.get("houseCode"));
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.likeRight("house_code",code);
        houseMapper.delete(wrapper);
        return null;
    }

    @Override
    public SysHouse findById(Integer id) {
        return houseMapper.selectById(id);
    }

    @Override
    public Integer update(SysHouse sysHouse) {
        Integer result=0;
        if (sysHouse != null){
            QueryWrapper<SysHouse> wrapper = new QueryWrapper<>();
            wrapper.eq("id",sysHouse.getId());
            result = houseMapper.update(sysHouse,wrapper);
        }
        return result;
    }

    @Override
    public Integer add(SysHouse sysHouse) {
        return houseMapper.insert(sysHouse);
    }

    @Override
    public List<Map<String, Object>> countBuildPeople() {
        QueryWrapper<SysHouse> wrapper = new QueryWrapper<>();
        wrapper.select("building_name","COUNT(building_name) AS count")
                .eq("is_live",1)
                .groupBy("building_id");
        return houseMapper.selectMaps(wrapper);
    }
}
