package com.strde.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strde.service.modules.system.admin.dto.AdminDto;
import com.strde.shiro.utils.ShiroUtils;

/**
 * 
 * <Controller公共组件.>
 * 
 * @author Old曾
 * @version [版本号, 2022年5月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    protected AdminDto getAdmin() {
        return ShiroUtils.getAdmin();
    }
    
    protected Long getAdminId() {
        return ShiroUtils.getAdminId();
    }
}
