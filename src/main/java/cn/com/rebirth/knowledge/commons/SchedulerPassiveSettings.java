/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons SchedulerPassiveSettings.java 2012-8-28 14:18:19 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import cn.com.rebirth.commons.service.middleware.annotation.Rmi;

/**
 * The Interface SchedulerPassiveSettings.
 *
 * @author l.xue.nong
 */
@Rmi(targetObject = "cn.com.rebirth.knowledge.scheduler.SchedulerPassiveSettingsImpl")
public interface SchedulerPassiveSettings extends PassiveSettings {

}
