package com.ylz.login.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylz.login.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ylz.login.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ylz
 * @since 2021-03-02
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectByName(String userName);
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    IPage<UserInfo> searchUserInfo(Page<Map<String, Object>> page, @Param(Constants.WRAPPER) QueryWrapper<Map<String, Object>> wrapper);

}
