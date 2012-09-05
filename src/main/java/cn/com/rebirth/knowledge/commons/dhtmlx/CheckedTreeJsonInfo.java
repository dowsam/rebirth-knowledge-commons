/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons CheckedTreeJsonInfo.java 2012-8-22 14:56:21 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx;

/**
 * The Class CheckedTreeJsonInfo.
 *
 * @author l.xue.nong
 */
public class CheckedTreeJsonInfo extends TreeJsonInfo {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1041527009264635839L;

	/** The checked. */
	private boolean checked = false;

	/**
	 * Checks if is checked.
	 *
	 * @return true, if is checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Sets the checked.
	 *
	 * @param checked the new checked
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
