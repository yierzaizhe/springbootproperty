package com.ylz.system.service;

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
 * @since 2021-03-29
 */
public interface ISysCommunityService extends IService<SysCommunity> {
    List<SysCommunity> findAll();


}
