package com.ylz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.system.entity.SysParking;
import com.ylz.system.entity.SysStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 小区后勤的库存 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
public interface ISysStockService extends IService<SysStock> {
    //按条件查找
    public IPage<SysStock> searchBy(Map<String,Object> param);

    public int count(Map<String,Object> param);
    //删除
    public Integer delete(Map<String,Object> param);
    //按id查信息
    public SysParking findById(Integer id);
    //更新信息
    public Integer update(SysStock stock);

    public Integer add(SysStock stock);
}
