package com.strde.shiro.service;

import com.slipper.service.modules.system.token.entity.TokenEntity;
import com.slipper.service.modules.system.admin.dto.AdminDto;

import java.util.Set;

public interface ShiroService {
    
    /**
     * 获取token
     * 
     * @param token
     * @return
     */
    TokenEntity queryTokenByToken(String token);
    
    /**
     * 获取用户信息
     * 
     * @param adminId
     * @return
     */
    AdminDto queryAdminByAdminId(Long adminId);
    
    /**
     * 获取用户权限
     * 
     * @param adminId
     * @return
     */
    Set<String> queryPermissionByAdminId(Long adminId);
}
