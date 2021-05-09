package com.ylz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.system.entity.SysBuilding;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ylz.system.entity.SysCommunity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区的楼栋 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-05-06
 */
public interface ISysBuildingService extends IService<SysBuilding> {

    public List<SysBuilding> getAll();
    //按条件查找
    public IPage<SysBuilding> searchBy(Map<String,Object> param);
    //删除
    public Integer delete(Map<String,Object> param);
    //按id查信息
    public SysBuilding findById(Integer id);
    //更新信息
    public Integer update(SysBuilding sysBuilding);

    public Integer add(SysBuilding SysBuilding);

}
