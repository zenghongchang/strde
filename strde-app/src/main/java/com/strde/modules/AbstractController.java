package com.strde.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strde.service.modules.system.admin.dto.AdminDto;
import com.strde.shiro.utils.ShiroUtils;

/**
 * Controller公共组件
 *
 * @author Loafer
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
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
