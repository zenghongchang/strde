package com.strde.shiro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.strde.service.modules.system.admin.dto.AdminDto;

/**
 * 
 * <Shiro 工具类>
 * @author  Henry(fba02)
 * @version  [版本号, 2022-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ShiroUtils {
    
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    
    public static AdminDto getAdmin() {
        return (AdminDto)SecurityUtils.getSubject().getPrincipal();
    }
    
    public static Long getAdminId() {
        return getAdmin().getId();
    }
    
    public static String getIp() {
        return getSubject().getSession().getHost();
    }    
}
