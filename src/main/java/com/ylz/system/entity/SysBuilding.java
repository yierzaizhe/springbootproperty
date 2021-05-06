package com.ylz.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 小区的楼栋
 * </p>
 *
 * @author ylz
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysBuilding implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 楼栋ID

     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 楼栋名称
     */
    private String name;

    /**
     * 共有几个单元
     */
    private Integer totalUnit;

    /**
     * 总层数

     */
    private Integer totalLevel;

    /**
     * 总户数

     */
    private Integer totalHouseholds;

    /**
     * 已住住户
     */
    private Integer existHouseholds;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间

     */
    private LocalDateTime createTime;


}
