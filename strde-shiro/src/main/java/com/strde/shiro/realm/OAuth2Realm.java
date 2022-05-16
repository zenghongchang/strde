package com.strde.shiro.realm;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.slipper.common.utils.Constant;
import com.slipper.common.utils.R;
import com.slipper.service.modules.system.admin.dto.AdminDto;
import com.slipper.service.modules.system.token.entity.TokenEntity;
import com.strde.shiro.service.ShiroService;
import com.strde.shiro.token.OAuth2Token;

@Component
public class OAuth2Realm extends AuthorizingRealm {
    // TODO: 注意 敲黑板 （BeanPostProcessor） 不可以直接注入服务层到 shiro 不然会导致 事务注解失效
    // TODO: 所以这边写个 shiro 服务层 依旧注意 shiro 服务层里面也是不可以注入服务层
    /*
     * @Resource TokenService tokenService;
     * 
     * @Resource AdminService adminService;
     * 
     * @Resource MenuService menuService;
     */
    @Resource
    ShiroService shiroService;
    
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }
    
    /**
     * 身份验证（凭证验证-token） TODO: 验证token是否有效
     * 
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        // 用户请求携带的token凭证
        String token = (String)authenticationToken.getPrincipal();
        
        // TokenEntity TokenEntity = tokenService.queryByToken(token);
        TokenEntity tokenEntity = shiroService.queryTokenByToken(token);
        if (tokenEntity == null || tokenEntity.getExpiredAt().getTime() < new Date().getTime()) {
            // 凭证不正确异常
            String message = JSONUtils.toJSONString(R.error(Constant.TOKEN_EXPIRE_CODE, Constant.TOKEN_EXPIRE));
            throw new IncorrectCredentialsException(message);
        }
        
        // AdminDto adminDto = adminService.queryById(tokenEntity.getAdminId());
        AdminDto adminDto = shiroService.queryAdminByAdminId(tokenEntity.getAdminId());
        if (adminDto == null || adminDto.getStatus() == 0) {
            // 账户锁定异常
            String message = JSONUtils.toJSONString(R.error(Constant.WARNING_CODE, "账户已被冻结，请联系管理员"));
            throw new LockedAccountException(message);
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(adminDto, token, getName());
        
        return simpleAuthenticationInfo;
    }
    
    /**
     * 授权验证（权限验证-permission）
     * 
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前用户信息
        AdminDto adminDto = (AdminDto)principalCollection.getPrimaryPrincipal();
        Long adminId = adminDto.getId();        
        // 获取权限
        // Set<String> permissions = menuService.queryPermission(adminId);
        Set<String> permissions = shiroService.queryPermissionByAdminId(adminId);        
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);        
        return simpleAuthorizationInfo;
    }    
}