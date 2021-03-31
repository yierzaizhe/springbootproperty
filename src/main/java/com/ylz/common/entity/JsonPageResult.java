package com.ylz.common.entity;

import com.ylz.common.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回实体
 * @author ylz
 * @date 2021-03-02-11:50
 */
@Data
public class JsonPageResult<T> implements Serializable {
    private Boolean success;
    private Integer errorCode;
    private String errorMsg;
    private String jwtToken;
    private T data;
    private Long total;
    public JsonPageResult() {
    }

    public JsonPageResult(boolean success) {
        this.success = success;
        this.errorCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
    }



    public JsonPageResult(boolean success, T data,long total) {
        this.success = success;
        this.errorCode = ResultCode.SUCCESS.getCode();
        this.errorMsg = ResultCode.SUCCESS.getMessage();
        this.data = data;
        this.total = total;
    }

   /* public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }*/
}
