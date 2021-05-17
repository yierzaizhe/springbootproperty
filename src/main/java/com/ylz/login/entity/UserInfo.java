package com.ylz.login.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ylz
 * @Description
 * @date 2021-05-17-12:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo {

    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 上一次登录时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime last_login_time;

    /**
     * 账号是否可用。默认为1（可用）
     */
    private Boolean enabled;

    /**
     * 是否过期。默认为1（没有过期）
     */
    private Boolean account_not_expired;

    /**
     * 账号是否锁定。默认为1（没有锁定）
     */
    private Boolean account_not_locked;

    /**
     * 证书（密码）是否过期。默认为1（没有过期）
     */
    private Boolean credentials_not_expired;

    /**
     * 创建时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime creat_time;

    /**
     * 修改时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime update_time;

    /**
     * 创建人
     */
    private Integer create_user;

    /**
     * 修改人
     */
    private Integer update_user;

    private Integer role_id;

    private String role_code;

    /**
     * 角色名
     */
    private String role_name;

    /**
     * 角色说明
     */
    private String role_description;

}
