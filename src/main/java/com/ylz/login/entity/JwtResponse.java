package com.ylz.login.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ylz
 * @Description 相应封装，主要包含jwttoken字段，直接返回对象即可
 * @date 2021-03-11-16:46
 */
@Data
public class JwtResponse implements Serializable {
    private static final long serialVersionUID=1L;
    private String JwtToken;
    public JwtResponse(String JwtToken) {
        this.JwtToken = JwtToken;
    }

}
