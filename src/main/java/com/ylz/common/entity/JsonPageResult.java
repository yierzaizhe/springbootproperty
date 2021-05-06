package com.ylz.common.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    private long total;
    private long totalPages;
    private long currentPage;

    public JsonPageResult() {
    }

    public JsonPageResult(boolean success) {
        this.success = success;
        this.errorCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
    }

    public JsonPageResult(boolean success, T data,long currentPage,long pages ,long total) {
        this.success = success;
        this.errorCode = ResultCode.SUCCESS.getCode();
        this.errorMsg = ResultCode.SUCCESS.getMessage();
        this.data = data;
        this.currentPage = currentPage;
        this.totalPages = pages;
        this.total = total;

    }

}
