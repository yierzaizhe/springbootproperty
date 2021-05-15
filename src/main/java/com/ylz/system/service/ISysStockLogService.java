package com.ylz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.system.entity.SysParking;
import com.ylz.system.entity.SysStock;
import com.ylz.system.entity.SysStockLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 小区后勤的库存采购日志 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-05-15
 */
public interface ISysStockLogService extends IService<SysStockLog> {
    //按条件查找
    public IPage<SysStockLog> searchBy(Map<String,Object> param);

    public int count(Map<String,Object> param);
    //删除
    public Integer delete(Map<String,Object> param);
    //按id查信息
    public SysParking findById(Integer id);
    //更新信息
    public Integer update(SysStockLog stock);

    public Integer add(SysStockLog stock);

    public Boolean updateStatus(String status, Integer id);
}
