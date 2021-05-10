package com.ylz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.system.entity.SysBuilding;
import com.ylz.system.entity.SysHouse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 房屋信息 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-05-06
 */
public interface ISysHouseService extends IService<SysHouse> {
    //按条件查找
    public IPage<SysHouse> searchBy(Map<String,Object> param);
    //删除
    public Integer delete(Map<String,Object> param);
    //按id查信息
    public SysHouse findById(Integer id);
    //更新信息
    public Integer update(SysHouse sysHouse);

    public Integer add(SysHouse sysHouse);
}
