package com.strde.service.modules.login.service;

import com.strde.service.modules.login.vo.LoginVo;
import com.strde.service.modules.system.token.entity.TokenEntity;

/**
 * 登录
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface LoginService {

    TokenEntity login(LoginVo loginVo);

    void logout(Long adminId);

}
