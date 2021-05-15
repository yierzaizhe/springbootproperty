package com.ylz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.system.entity.SysHouse;
import com.ylz.system.entity.SysParking;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * <p>
 * 车位表 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
public interface ISysParkingService extends IService<SysParking> {
    //按条件查找
    public IPage<SysParking> searchBy(Map<String,Object> param);

    public int count(Map<String,Object> param);
    //删除
    public Integer delete(Map<String,Object> param);
    //按id查信息
    public SysParking findById(Integer id);
    //更新信息
    public Integer update(SysParking sysParking);

    public Integer add(SysParking sysParking);
}
