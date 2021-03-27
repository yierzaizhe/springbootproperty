package com.ylz.login.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ylz
 * @Description JwtRequest，请求封装，主要包含username和password字段，
 *              前台发后台的时候发json，@RequestBody可以直接转换。
 * @date 2021-03-11-16:43
 */
@Data
public class JwtRequest implements Serializable {
    private static final long serialVersionUID=1L;
    private String userName;
    private String password;

}
