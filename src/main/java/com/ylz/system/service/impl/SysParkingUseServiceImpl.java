package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysHouseLive;
import com.ylz.system.entity.SysParking;
import com.ylz.system.entity.SysParkingUse;
import com.ylz.system.mapper.SysParkingMapper;
import com.ylz.system.mapper.SysParkingUseMapper;
import com.ylz.system.service.ISysParkingUseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@Service
public class SysParkingUseServiceImpl extends ServiceImpl<SysParkingUseMapper, SysParkingUse> implements ISysParkingUseService {
    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private SysParkingUseMapper parkingUseMapper;

    @Override
    public IPage<SysParkingUse> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysParkingUse> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("parkingCode"))){
                wrapper.like("parking_code",param.get("parkingCode"));
            }
            if (!StringUtil.isEmpty((String) param.get("carCard"))){
                wrapper.like("car_card",param.get("carCard"));
            }
            if (!StringUtil.isEmpty((String) param.get("ownerName"))){
                wrapper.like("owner_name",param.get("ownerName"));
            }
            if (!StringUtil.isEmpty((String) param.get("useType"))){
                wrapper.ge("use_type",param.get("useType"));
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
        return parkingUseMapper.selectPage(page,wrapper);
    }

    @Override
    public int count(Map<String, Object> param) {
        QueryWrapper<SysParkingUse> wrapper = new QueryWrapper<>();
        if (param == null || StringUtil.isEmpty((String) param.get("code")) ){
            return 0;
        }
        wrapper.eq("code",param.get("code"));
        return parkingUseMapper.selectCount(wrapper);
    }

    @Override
    public Integer delete(Map<String, Object> param) {
        int id = (int) param.get("id");
        return parkingUseMapper.deleteById(id);
    }

    @Override
    public SysParkingUse findById(Integer id) {
        return parkingUseMapper.selectById(id);
    }

    @Override
    public Integer update(SysParkingUse sysParkingUse) {
        Integer result=0;
        if (sysParkingUse != null){
            QueryWrapper<SysParkingUse> wrapper = new QueryWrapper<>();
            wrapper.eq("id",sysParkingUse.getId());
            result = parkingUseMapper.update(sysParkingUse,wrapper);
        }
        return result;
    }

    @Override
    public Integer add(SysParkingUse sysParkingUse) {
        return parkingUseMapper.insert(sysParkingUse);
    }

    @Override
    public List<Map<String, Object>> countParkType() {
        QueryWrapper<SysParkingUse> wrapper = new QueryWrapper<>();
        wrapper.select("use_type as name","COUNT(use_type) AS value")
                .groupBy("use_type");
        return parkingUseMapper.selectMaps(wrapper);
    }
}
