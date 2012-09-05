/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons InitiativeSettings.java 2012-8-28 11:27:52 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import cn.com.rebirth.commons.service.middleware.annotation.Rmi;

/**
 * The Interface InitiativeSettings.
 *
 * @author l.xue.nong
 */
@Rmi(targetObject = "cn.com.rebirth.knowledge.web.admin.service.InitiativeSettingsImpl")
public interface InitiativeSettings extends SettingsShare {

}
