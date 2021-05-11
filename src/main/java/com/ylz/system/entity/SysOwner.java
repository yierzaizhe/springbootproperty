package com.ylz.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 小区所有住户
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysOwner implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 门牌号

     */
    private String houseCode;

    /**
     * 名字
     */
    private String name;

    /**
     * 性别 1 男  0女
     */
    private Boolean sex;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 联系方式
     */
    private String telephone;

    /**
     * 是否为户主 1 是  默认0 不是
     */
    private Boolean isMain;

    /**
     * 住户类型，购买的 0   租 1
     */
    private Boolean type;

    /**
     * 职业信息
     */
    private String profession;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间

     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;

    /**
     * 出生日期
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime birthday;


}
