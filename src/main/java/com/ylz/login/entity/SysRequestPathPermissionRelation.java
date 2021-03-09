package com.ylz.login.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 路径权限关联表
 * </p>
 *
 * @author ylz
 * @since 2021-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRequestPathPermissionRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 请求路径id
     */
    private Integer urlId;

    /**
     * 权限id
     */
    private Integer permissionId;


}
