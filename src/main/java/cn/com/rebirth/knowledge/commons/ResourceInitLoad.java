/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-web-admin ResourceInitLoad.java 2012-8-20 10:16:29 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import cn.com.rebirth.commons.settings.ThreadSafeVariableSettings;

/**
 * The Interface ResourceInitLoad.
 *
 * @author l.xue.nong
 */
public interface ResourceInitLoad {

	/**
	 * Load resource.
	 *
	 * @param builder the builder
	 */
	void loadResource(ThreadSafeVariableSettings.Builder builder);
}
