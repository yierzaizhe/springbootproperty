package com.ylz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.system.entity.SysHouse;
import com.ylz.system.entity.SysHouseLive;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 房屋居住情况 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
public interface ISysHouseLiveService extends IService<SysHouseLive> {
    //按条件查找
    public IPage<SysHouseLive> searchBy(Map<String,Object> param);
    public int count(Map<String,Object> param);
    //删除
    public Integer delete(Map<String,Object> param);
    //按id查信息
    public SysHouseLive findById(Integer id);
    //更新信息
    public Integer update(SysHouseLive sysHouseLive);

    public Integer add(SysHouseLive sysHouseLive);
}
