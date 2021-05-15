package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysHouseLive;
import com.ylz.system.entity.SysOwner;
import com.ylz.system.entity.SysParking;
import com.ylz.system.mapper.SysParkingMapper;
import com.ylz.system.service.ISysParkingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 车位表 服务实现类
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@Service
public class SysParkingServiceImpl extends ServiceImpl<SysParkingMapper, SysParking> implements ISysParkingService {

    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private SysParkingMapper parkingMapper;

    @Override
    public IPage<SysParking> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysParking> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("code"))){
                wrapper.like("code",param.get("code"));
            }
            if (!StringUtil.isEmpty((String) param.get("name"))){
                wrapper.like("name",param.get("name"));
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
        return parkingMapper.selectPage(page,wrapper);
    }

    @Override
    public int count(Map<String, Object> param) {
        QueryWrapper<SysParking> wrapper = new QueryWrapper<>();
        if (param == null || StringUtil.isEmpty((String) param.get("code")) ){
            return 0;
        }
        wrapper.eq("code",param.get("code"));
        return parkingMapper.selectCount(wrapper);
    }

    @Override
    public Integer delete(Map<String, Object> param) {
        int id = (int) param.get("id");
        return parkingMapper.deleteById(id);
    }

    @Override
    public SysParking findById(Integer id) {
        return parkingMapper.selectById(id);
    }

    @Override
    public Integer update(SysParking sysParking) {
        Integer result=0;
        if (sysParking != null){
            QueryWrapper<SysParking> wrapper = new QueryWrapper<>();
            wrapper.eq("id",sysParking.getId());
            result = parkingMapper.update(sysParking,wrapper);
        }
        return result;
    }

    @Override
    public Integer add(SysParking sysParking) {
        return parkingMapper.insert(sysParking);
    }
}
