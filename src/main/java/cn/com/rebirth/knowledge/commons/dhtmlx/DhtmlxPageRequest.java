/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-web-admin DhtmlxPageRequest.java 2012-8-9 10:48:49 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

import cn.com.rebirth.commons.PageRequest;

/**
 * The Class DhtmlxPageRequest.
 *
 * @author l.xue.nong
 */
public class DhtmlxPageRequest extends PageRequest {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7541228919951787823L;

	/** The pos start. */
	private int posStart;

	/** The count. */
	private int count;

	/**
	 * Gets the pos start.
	 *
	 * @return the pos start
	 */
	public int getPosStart() {
		return posStart;
	}

	/**
	 * Sets the pos start.
	 *
	 * @param posStart the new pos start
	 */
	public void setPosStart(int posStart) {
		this.posStart = posStart;
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int getPageSize() {
		return getCount();
	}

	@Override
	public int getOffset() {
		return getPosStart();
	}

}
