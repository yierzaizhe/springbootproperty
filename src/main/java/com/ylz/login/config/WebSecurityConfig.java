package com.ylz.login.config;

import com.ylz.login.config.handler.*;
import com.ylz.login.config.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import static org.apache.commons.lang3.BooleanUtils.and;

/**
 * @author ylz
 * @date 2021-03-02-23:09
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        //设置默认加密方式，强hash加密
        return new BCryptPasswordEncoder();

    }
    //匿名用户访问无权限资源时的异常
    @Autowired
    CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    //登陆成功
    @Autowired
    CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;
    //登录失败
    @Autowired
    CustomizeAuthenticationFailureHandler authenticationFailureHandler;
    //退出
    @Autowired
    CustomizeLogoutSuccessHandler logoutSuccessHandler;
    //会话被挤下线
    @Autowired
    CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    //访问决策管理器
    @Autowired
    CustomizeAccessDecisionManager accessDecisionManager;
    //实现权限拦截
    @Autowired
    CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;
    @Autowired
    private CustomizeAbstractSecurityInterceptor securityInterceptor;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式等
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http相关的配置，包括登入登出、异常处理、会话管理等
        //跨域
        http.cors().and().csrf().disable();

        http.authorizeRequests().
                /*antMatchers("/getUser").hasAnyAuthority("query_user").*/
                withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager);//决策管理器
                        o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源
                        return o;
                    }
                }).
                //登录
                and().formLogin().
                    permitAll(). //允许所有用户
                    successHandler(authenticationSuccessHandler).
                    failureHandler(authenticationFailureHandler).
                //退出
                and().logout().
                    permitAll(). //允许所有用户
                    logoutSuccessHandler(logoutSuccessHandler).
                    deleteCookies("JSESSIONID").//登出之后删除cookie
                //异常处理(权限拒绝、登录失效等)
                and().exceptionHandling().
                    authenticationEntryPoint(authenticationEntryPoint).//匿名用户访问无权限资源时的异常处理
                //限制一个账号登录
                and().sessionManagement().
                    maximumSessions(1).//同一账号同时登录最大用户数
                    expiredSessionStrategy(sessionInformationExpiredStrategy);//会话信息过期策略会话信息过期策略(账号被挤下线)





    }

}
