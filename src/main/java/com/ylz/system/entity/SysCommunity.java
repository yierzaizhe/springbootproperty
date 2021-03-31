package com.ylz.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 小区表
 * </p>
 *
 * @author ylz
 * @since 2021-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysCommunity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 小区id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 小区编号
     */
    private String code;

    /**
     * 小区名称
     */
    private String name;

    /**
     * 坐落地址
     */
    private String address;

    /**
     * 占地面积（m2）
     */
    private Double area;

    /**
     * 总栋数
     */
    private Integer totalBuildings;

    /**
     * 总户数
     */
    private Integer totalHouseholds;

    /**
     * 绿化率（%）
     */
    private Integer greeningRate;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 开发商名称
     */
    private String developer;

    /**
     * 物业公司名称
     */
    private String estateCompany;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 状态:0-启用（默认），1-不启用
     */
    private String status;


}
