/*
 * Copyright (c) 2005-2012 www.china-cti.com All rights reserved
 * Info:rebirth-knowledge-commons PageSetting.java 2012-8-3 21:06:52 l.xue.nong$$
 */
package cn.com.rebirth.knowledge.commons.dhtmlx.entity;

/**
 * The Class PageSetting.
 *
 * @author l.xue.nong
 */
public class PageSetting {

	/** The enabled. */
	private boolean enabled = true;

	/** The size. */
	private int size;

	/** The size list. */
	private String sizeList;

	/** The style. */
	private String style = "background-color:white;overflow:hidden";

	/** The position. */
	private String position = "bottom";

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the size list.
	 *
	 * @return the size list
	 */
	public String getSizeList() {
		return sizeList;
	}

	/**
	 * Sets the size list.
	 *
	 * @param sizeList the new size list
	 */
	public void setSizeList(String sizeList) {
		this.sizeList = sizeList;
	}

	/**
	 * Gets the style.
	 *
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * Sets the style.
	 *
	 * @param style the new style
	 */
	public void setStyle(String style) {
		this.style = style;
	}
}