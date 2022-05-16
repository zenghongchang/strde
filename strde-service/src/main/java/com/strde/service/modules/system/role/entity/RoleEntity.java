package com.strde.service.modules.system.role.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.strde.common.validator.group.Create;
import com.strde.common.validator.group.Update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
public class RoleEntity implements Serializable {
    
    /**
     *
     */
    @NotNull(message = "ID不能为空", groups = Update.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 名称
     */
    @NotBlank(message = "角色名称不能为空", groups = {Create.class, Update.class})
    private String name;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 是否显示
     */
    @JsonProperty("is_display")
    private Integer isDisplay;
    
    /**
     * 创建者
     */
    private Long creator;
    
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private Date createdAt;
    
    /**
     * 更新者
     */
    private Long updater;
    
    /**
     * 更新时间
     */
    @JsonProperty("updated_at")
    private Date updatedAt;
    
    /**
     * 
     */
    @TableLogic
    private Integer deleted;
    
    /**
     * 菜单ID
     */
    @JsonProperty("menu_ids")
    @TableField(exist = false)
    private List<Long> menuIds;
    
}
