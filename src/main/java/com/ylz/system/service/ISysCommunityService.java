package com.ylz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.system.entity.SysCommunity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区表 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-03-31
 */
public interface ISysCommunityService extends IService<SysCommunity> {

    public IPage<SysCommunity> getAll();

    public IPage<SysCommunity> searchBy(Map<String,Object> param);

    public SysCommunity findById(Integer id);

    public Integer add(SysCommunity sysCommunity);

    public Integer delete(List<Integer> id);

    public Integer update(SysCommunity sysCommunity);

    public Boolean updateStatus(String status, Integer id);
}
