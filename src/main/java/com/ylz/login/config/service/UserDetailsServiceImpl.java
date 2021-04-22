package com.ylz.login.config.service;

import com.ylz.login.entity.SysPermission;
import com.ylz.login.entity.SysUser;
import com.ylz.login.service.ISysPermissionService;
import com.ylz.login.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ylz
 * @date 2021-03-02-22:27
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysPermissionService permissionService;

    /**
     * 根据用户名查询用户信息与用户权限
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
            if (userName == null || "".equals(userName)){
            throw new RuntimeException("用户名不能为空");
        }
        SysUser user=userService.selectByName(userName);
        if (user == null){
            throw new RuntimeException("用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user != null){
            //获取用户的所有信息
            List<SysPermission> permissionList=permissionService.selectListByUser(user.getId());
            permissionList.forEach(sysPermission -> {
                GrantedAuthority grantedAuthority= new SimpleGrantedAuthority(sysPermission.getPermissionCode());
                grantedAuthorities.add(grantedAuthority);
            });
        }

        //需要构造出 org.springframework.security.core.userdetails.User 对象并返回
        return new User(user.getAccount(),user.getPassword(),user.getEnabled(),user.getAccountNotExpired(),user.getCredentialsNotExpired(),user.getAccountNotLocked(),grantedAuthorities);
    }
}
