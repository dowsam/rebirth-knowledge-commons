/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons WebPassiveSettings.java 2012-8-30 15:05:31 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import cn.com.rebirth.commons.service.middleware.annotation.Rmi;

/**
 * The Interface WebPassiveSettings.
 *
 * @author l.xue.nong
 */
@Rmi(targetObject = "cn.com.rebirth.knowledge.web.WebPassiveSettingsImpl")
public interface WebPassiveSettings extends PassiveSettings {

}
