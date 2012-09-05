/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons LogService.java 2012-8-14 10:07:35 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.service;

import cn.com.rebirth.commons.service.middleware.annotation.Rmi;
import cn.com.rebirth.knowledge.commons.entity.system.AbstractLogBaseEntity;

/**
 * The Interface LogService.
 *
 * @author l.xue.nong
 */
@Rmi(targetObject = "cn.com.rebirth.knowledge.web.admin.service.LogServiceImpl")
public interface LogService {

	/**
	 * Adds the log.
	 *
	 * @param abstractLogBaseEntity the abstract log base entity
	 */
	void addLog(AbstractLogBaseEntity abstractLogBaseEntity);
}
