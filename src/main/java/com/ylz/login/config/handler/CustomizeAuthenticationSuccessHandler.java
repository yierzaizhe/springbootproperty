package com.ylz.login.config.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ylz.common.entity.JsonResult;
import com.ylz.common.utils.*;
import com.ylz.login.entity.SysUser;
import com.ylz.login.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ylz
 * @Description 登陆成功逻辑
 * @date 2021-03-03-22:41
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Value("${token.expirationSeconds}")
    private int expirationSeconds;

    @Value("${token.validTime}")
    private int validTime;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ISysUserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //更新用户表登陆时间等信息
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser=userService.selectByName(userDetails.getUsername());
        LocalDateTime now = LocalDateTime.now();
        sysUser.setLastLoginTime(now);
        sysUser.setUpdateTime(now);
        sysUser.setUpdateUser(sysUser.getId());
        userService.update(sysUser);
        String jwtToken = null;
        String ip = AccessAddressUtil.getIpAddress(httpServletRequest);
        JSONObject subject = new JSONObject(true);
        subject.put("ip", ip);
        subject.put("userName", userDetails.getUsername());
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String authToken = authHeader.substring("Bearer".length());
            authToken =authToken.replace("\"", "");
            jwtToken = authToken;
        }else {
            jwtToken=JwtTokenUtil.generateToken(userDetails.getUsername(),expirationSeconds, subject);
        }

        //如登录成功之后返回给前台当前用户菜单权限，
        //进而前台动态的控制菜单的显示
        //返回json数据
        //生成token
        //获取请求的ip地址

        /*map.put("Authorities",userDetails.getAuthorities());*/

        //String jwtToken = JwtTokenUtil.generateToken(userDetails.getUsername(),expirationSeconds, subject);
        /*String jwtToken = null;
        try {
            //jwtToken = JjwtUtil.createJWT(userDetails.getUsername(),userDetails.getUsername(),userDetails.getUsername());
            jwtToken = JWTUtils.createJWT(StringUtil.getUUID(),subject.toJSONString(),expirationSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //刷新时间
        Integer expire = validTime*24*60*60*1000;
        //获取请求的ip地址
        String currentIp = AccessAddressUtil.getIpAddress(httpServletRequest);
        redisUtil.setTokenRefresh(jwtToken,userDetails.getUsername(),currentIp);
        JsonResult result = ResultTool.success(jwtToken);
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));

    }
}
