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
 * 小区后勤的库存
 * </p>
 *
 * @author ylz
 * @since 2021-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 采购的物品
     */
    private String goods;

    /**
     * 用途
     */
    private String remark;

    /**
     * 数量
     */
    private Integer total;

    /**
     * 物业人员的用户名
     */
    private String username;

    /**
     * 采购费用
     */
    private String totalFee;

    /**
     * 审核是否通过 0 默认    1--》审核通过
     */
    private String isExamine;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;


}
