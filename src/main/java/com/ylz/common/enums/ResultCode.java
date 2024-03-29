package com.ylz.common.enums;

/**
 * @author ylz
 * @date 2021-03-02-00:49
 * 规定:
 *   #1表示成功
 *   #1001～1999 区间表示参数错误
 *   #2001～2999 区间表示用户错误
 *   #3001～3999 区间表示接口异常
 */
public enum   ResultCode {
    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    //USER_NOT_LOGIN(2001, "用户未登录"),
    USER_NOT_LOGIN(2001, "没有该权限"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号未赋权"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    TOKEN_IS_BLACKLIST(4001,"此token为黑名单"),
    LOGIN_IS_OVERDUE(4002,"登录已失效"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限"),
    /*小区*/
    COMMUNITY_ADD_FAILED(5001,"新增小区失败，请检查参数！"),
    COMMUNITY_DEL_FAILED(5002,"删除小区失败，请检查参数！"),
    COMMUNITY_UP_FAILED(5003,"更新小区失败，请检查参数！"),
    COMMUNITY_UPSTA_FAILED(5004,"更新小区状态失败，请检查参数！"),
    /*楼房*/
    BUILD_ADD_FAILED(5001,"新增房产失败，请检查参数！"),
    BUILD_DEL_FAILED(5002,"删除房产失败，请检查参数！"),
    BUILD_UP_FAILED(5003,"更新房产失败，请检查参数！"),
    BUILD_UPSTA_FAILED(5004,"更新房产状态失败，请检查参数！"),
    /*业主*/
    OWNER_ADD_FAILED(5001,"新增业主信息失败，请检查参数！"),
    OWNER_ADD_HOUSE_FAILED(5001,"该门牌号为空号，请检查参数！"),
    OWNER_DEL_FAILED(5002,"删除业主信息失败，请检查参数！"),
    OWNER_UP_FAILED(5003,"更新业主信息失败，请检查参数！"),
    OWNER_UPSTA_FAILED(5004,"更新业主信息状态失败，请检查参数！"),
    /*居住情况*/
    LIVE_ADD_FAILED(5001,"新增居住信息失败，请检查参数！"),
    LIVE_DEL_FAILED(5002,"删除居住信息失败，请检查参数！"),
    LIVE_UP_FAILED(5003,"更新居住信息失败，请检查参数！"),
    LIVE_EXIST_FAILED(5004,"该房屋已经居住，请检查参数！"),
    LIVE_UPSTA_FAILED(5005,"更新居住信息状态失败，请检查参数！"),
    /*车位情况*/
    PARK_ADD_FAILED(5001,"新增车位信息失败，请检查参数！"),
    PARK_DEL_FAILED(5002,"删除车位信息失败，请检查参数！"),
    PARK_UP_FAILED(5003,"更新车位信息失败，请检查参数！"),
    PARK_EXIST_FAILED(5004,"该车位已经使用，请检查参数！"),
    PARK_UPSTA_FAILED(5005,"更新车位信息状态失败，请检查参数！"),
    PARK_NOT_FAILED(5005,"该车位不存在，请检查参数！"),
    PAY_ADD_FAILED(5001,"该车辆未完成上次出场！");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
