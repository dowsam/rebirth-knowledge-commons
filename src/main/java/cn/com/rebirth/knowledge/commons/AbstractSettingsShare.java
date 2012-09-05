/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons AbstractSettingsShare.java 2012-8-28 11:20:14 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import cn.com.rebirth.commons.settings.Settings;

/**
 * The Class AbstractSettingsShare.
 *
 * @author l.xue.nong
 */
public abstract class AbstractSettingsShare implements SettingsShare {

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.SettingsShare#share(cn.com.rebirth.commons.settings.Settings)
	 */
	@Override
	public void passive(Settings settings) {

	}

	/* (non-Javadoc)
	 * @see cn.com.rebirth.knowledge.commons.SettingsShare#build()
	 */
	@Override
	public Settings initiative() {
		throw new UnsupportedOperationException("not yet implement");
	}

}
