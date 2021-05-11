package com.ylz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.system.entity.SysHouseLive;
import com.ylz.system.entity.SysOwner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 小区所有住户 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
public interface ISysOwnerService extends IService<SysOwner> {

    //按条件查找
    public IPage<SysOwner> searchBy(Map<String,Object> param);
    //删除
    public Integer delete(Map<String,Object> param);
    //按id查信息
    public SysOwner findById(Integer id);
    //更新信息
    public Integer update(SysOwner sysOwner);

    public Integer add(SysOwner sysOwner);


}
