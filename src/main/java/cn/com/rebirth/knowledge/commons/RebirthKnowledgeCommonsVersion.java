/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons RebirthKnowledgeCommonsVersion.java 2012-7-19 13:18:36 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons;

import cn.com.rebirth.commons.AbstractVersion;
import cn.com.rebirth.commons.Version;

/**
 * The Class RebirthKnowledgeCommonsVersion.
 *
 * @author l.xue.nong
 */
public class RebirthKnowledgeCommonsVersion extends AbstractVersion implements Version {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4040042309816265782L;

	/* (non-Javadoc)
	 * @see cn.com.rebirth.commons.Version#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "Rebirth-Knowledge-Commons";
	}

}
