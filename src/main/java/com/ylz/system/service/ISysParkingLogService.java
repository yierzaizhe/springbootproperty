package com.ylz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.system.entity.SysParking;
import com.ylz.system.entity.SysParkingLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
public interface ISysParkingLogService extends IService<SysParkingLog> {
    //按条件查找
    public IPage<SysParkingLog> searchBy(Map<String,Object> param);

    public int count(Map<String,Object> param);
    //删除
    public Integer delete(Map<String,Object> param);
    //按id查信息
    public SysParkingLog findById(Integer id);
    //更新信息
    public Integer update(SysParkingLog sysParkingLog);

    public Integer add(SysParkingLog sysParkingLog);
}
