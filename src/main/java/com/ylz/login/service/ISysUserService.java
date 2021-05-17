package com.ylz.login.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ylz.login.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ylz.login.entity.UserInfo;
import com.ylz.system.entity.SysBuilding;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ylz
 * @since 2021-03-02
 */
public interface ISysUserService extends IService<SysUser> {

    //按条件查找
    public IPage<SysUser> searchBy(Map<String,Object> param);

    public IPage<UserInfo> searchAll(Map<String,Object> param);


    //删除
    public Integer delete(Map<String,Object> param);
    //按id查信息
    public SysBuilding findById(Integer id);
    //更新信息
    public Integer update_two(Map<String, Object> param) throws Exception;

    public Integer add(@RequestBody Map<String, Object> param) throws Exception;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Integer id);
    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);
    int restPassword(SysUser sysUser);
    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    SysUser selectByName(String userName);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(int offset, int limit);

}
