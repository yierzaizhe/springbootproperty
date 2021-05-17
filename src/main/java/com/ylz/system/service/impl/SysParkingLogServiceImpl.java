package com.ylz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.common.utils.StringUtil;
import com.ylz.system.entity.SysParking;
import com.ylz.system.entity.SysParkingLog;
import com.ylz.system.mapper.SysParkingLogMapper;
import com.ylz.system.service.ISysParkingLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
public class SysParkingLogServiceImpl extends ServiceImpl<SysParkingLogMapper, SysParkingLog> implements ISysParkingLogService {

    @Value("${page.currentPage}")
    private int currentPage;
    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private SysParkingLogMapper parkingLogMapper;
    @Override
    public IPage<SysParkingLog> searchBy(Map<String, Object> param) {
        //分页信息
        IPage page = new Page();
        QueryWrapper<SysParkingLog> wrapper = new QueryWrapper<>();
        //判断查询条件
        if(param != null){
            if (!StringUtil.isEmpty((String) param.get("carCode"))){
                wrapper.like("car_card",param.get("carCode"));
            }
            if (!StringUtil.isEmpty((String) param.get("carCard"))){
                wrapper.like("car_card",param.get("carCard"));
            }
            if (!StringUtil.isEmpty((String) param.get("havePark"))){
                wrapper.eq("have_park",param.get("havePark"));
            }
            if (!StringUtil.isEmpty((String) param.get("finish"))){
                wrapper.eq("finish",param.get("finish"));
            }
            if (!StringUtil.isEmpty((String) param.get("pay"))){
                wrapper.like("pay",param.get("pay"));
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
            }
            wrapper.orderByDesc("start_time");
        }
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return parkingLogMapper.selectPage(page,wrapper);
    }

    @Override
    public int count(Map<String, Object> param) {
        return 0;
    }

    @Override
    public Integer delete(Map<String, Object> param) {
        int id = (int) param.get("id");
        return parkingLogMapper.deleteById(id);
    }

    @Override
    public SysParkingLog findById(Integer id) {
        return null;
    }

    @Override
    public Integer update(SysParkingLog sysParkingLog) {
        Integer result=0;
        if (sysParkingLog != null){
            QueryWrapper<SysParkingLog> wrapper = new QueryWrapper<>();
            wrapper.eq("id",sysParkingLog.getId());
            result = parkingLogMapper.update(sysParkingLog,wrapper);
        }
        return result;
    }

    @Override
    public Integer add(SysParkingLog sysParkingLog) {
        return parkingLogMapper.insert(sysParkingLog);
    }
}
