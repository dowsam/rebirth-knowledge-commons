/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons OnlineUserService.java 2012-8-30 16:42:59 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import cn.com.rebirth.commons.service.middleware.annotation.Rmi;
import cn.com.rebirth.knowledge.commons.entity.system.OnlineSysUserEntity;

/**
 * The Interface OnlineUserService.
 *
 * @author l.xue.nong
 */
@Rmi(targetObject = "cn.com.rebirth.knowledge.web.admin.service.UserService")
public interface OnlineUserService extends Shell<OnlineSysUserEntity> {

	/**
	 * Removes the.
	 *
	 * @param sessionId the session id
	 */
	void t(String sessionId);
}
