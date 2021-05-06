package com.ylz.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 房屋信息
 * </p>
 *
 * @author ylz
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysHouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房屋id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属楼栋id
     */
    private Integer buildingId;

    /**
     * 楼栋名称
     */
    private String buildingName;

    /**
     * 所属单元楼

     */
    private Integer unit;

    /**
     * 层数
     */
    private Integer floor;

    /**
     * 门牌号
     */
    private String houseCode;

    /**
     * 关于房型面积的描述
     */
    private String description;

    /**
     * 是否有人居住
     */
    private Boolean isLive;

    /**
     * 房子平米数
     */
    private String area;

    /**
     * 业主id
     */
    private Integer ownerId;

    /**
     * 业主姓名
     */
    private String ownerName;

    /**
     * 居住类型，buy 购买 ，null 空房 ，rent 租房
     */
    private String kind;

    /**
     * 对房屋居住的解释
     */
    private String kindParam;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


}
