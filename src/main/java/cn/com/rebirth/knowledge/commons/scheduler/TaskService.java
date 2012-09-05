/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons TaskService.java 2012-8-6 9:10:56 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.scheduler;

import java.util.Collection;

import cn.com.rebirth.commons.service.middleware.annotation.Rmi;
import cn.com.rebirth.core.schedule.SchedulerService;

/**
 * The Interface TaskService.
 *
 * @author l.xue.nong
 */
@Rmi(targetObject = "cn.com.rebirth.knowledge.scheduler.impl.TaskServiceImpl", timeOut = 6000)
public interface TaskService extends SchedulerService {

	/**
	 * Gets the qrtz triggers.
	 *
	 * @param pageRequest the page request
	 * @return the qrtz triggers
	 */
	public Collection<QrtzTriggersJobDetails> getQrtzTriggers();
}
